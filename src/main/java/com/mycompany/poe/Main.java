package com.mycompany.poe;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        // ===== PART 1: REGISTRATION =====
        System.out.println("=".repeat(50));
        System.out.println("POE - REGISTRATION");
        System.out.println("=".repeat(50));

        System.out.println("Enter first name:");
        String firstName = input.nextLine();

        System.out.println("Enter last name:");
        String lastName = input.nextLine();

        System.out.println("Enter username (must contain _ and be max 5 characters):");
        String username = input.nextLine();

        System.out.println("Enter password (min 8 chars, 1 uppercase, 1 number, 1 special):");
        String password = input.nextLine();

        System.out.println("Enter SA cell number (+27):");
        String cell = input.nextLine();

        String registerMessage = login.registerUser(username, password, cell, firstName, lastName);
        System.out.println(registerMessage);

        if (!registerMessage.equals("User successfully registered.")) {
            System.out.println("Registration failed. Exiting...");
            return;
        }

        // ===== PART 1: LOGIN =====
        System.out.println("\n" + "=".repeat(50));
        System.out.println("POE - LOGIN");
        System.out.println("=".repeat(50));

        System.out.println("Enter username:");
        String loginUser = input.nextLine();

        System.out.println("Enter password:");
        String loginPass = input.nextLine();

        boolean success = login.loginUser(loginUser, loginPass);

        System.out.println(login.returnLoginStatus(success));

        if (!success) {
            System.out.println("Login failed. Exiting...");
            return;
        }

        // ===== PART 2: POE APPLICATION =====
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Welcome to QuickChat.");
        System.out.println("=".repeat(50));

        System.out.println("\nHow many messages would you like to send?");
        int maxMessages = input.nextInt();
        input.nextLine(); // Consume newline

        int messageCount = 0;
        ArrayList<Message> sentMessages = new ArrayList<>();

        // For loop to handle message input
        for (messageCount = 0; messageCount < maxMessages; messageCount++) {
            boolean running = true;
            
            while (running) {
                System.out.println("\n" + "-".repeat(40));
                System.out.println("MAIN MENU");
                System.out.println("-".repeat(40));
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                int choice = input.nextInt();
                input.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n" + "-".repeat(40));
                        System.out.println("SEND MESSAGE #" + (messageCount + 1));
                        System.out.println("-".repeat(40));

                        Message message = new Message();

                        // Generate Message ID
                        message.generateMessageID();
                        message.setMessageNumber(messageCount + 1);

                        // Get recipient cell
                        System.out.print("Enter recipient cell number (format: +27xxxxxxxxx): ");
                        String recipientCell = input.nextLine();
                        String cellResult = message.checkRecipientCell(recipientCell);
                        System.out.println(cellResult);

                        if (!cellResult.contains("successfully")) {
                            break;
                        }

                        // Get message
                        System.out.print("Enter your message (max 250 characters): ");
                        String messageText = input.nextLine();
                        String lengthResult = message.checkMessageLength(messageText);
                        System.out.println(lengthResult);

                        if (!lengthResult.contains("ready")) {
                            break;
                        }

                        // Create message hash
                        message.createMessageHash();

                        // Send, store, or disregard
                        System.out.println("\n" + "-".repeat(40));
                        System.out.println("MESSAGE OPTIONS");
                        System.out.println("-".repeat(40));
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Choose an option: ");

                        int messageChoice = input.nextInt();
                        input.nextLine(); // Consume newline

                        String sendResult = message.sentMessage(messageChoice);
                        System.out.println(sendResult);

                        if (messageChoice == 1 || messageChoice == 3) {
                            sentMessages.add(message);
                            System.out.println("\n" + "-".repeat(40));
                            System.out.println("MESSAGE DETAILS");
                            System.out.println("-".repeat(40));
                            System.out.println(message.printMessage());
                            System.out.println("-".repeat(40));
                        }

                        int remaining = maxMessages - (messageCount + 1);
                        System.out.println("\nMessages remaining: " + remaining);
                        running = false;
                        break;

                    case 2:
                        System.out.println("\n⏳ Coming Soon.");
                        break;

                    case 3:
                        System.out.println("\n" + "=".repeat(50));
                        System.out.println("SUMMARY");
                        System.out.println("=".repeat(50));
                        System.out.println("Total messages sent: " + sentMessages.size());
                        System.out.println("Thank you for using QuickChat, " + firstName + "!");
                        System.out.println("=".repeat(50));
                        
                        // Save messages to JSON
                        if (!sentMessages.isEmpty()) {
                            JSONUtil.saveMessagesToJSON(sentMessages);
                        }
                        
                        input.close();
                        return;

                    default:
                        System.out.println("\n❌ Invalid choice. Please try again.");
                }
                
                if (!running) {
                    break;
                }
            }
        }

        // After loop completes
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Total messages sent: " + sentMessages.size());
        System.out.println("Thank you for using QuickChat, " + firstName + "!");
        System.out.println("=".repeat(50));
        
        // Save messages to JSON
        if (!sentMessages.isEmpty()) {
            JSONUtil.saveMessagesToJSON(sentMessages);
        }

        input.close();
    }
}
