package com.mycompany.poe;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private int totalMessagesSent = 0;

    public Message() {
    }

    // Generate random 10-digit Message ID
    public String generateMessageID() {
        java.util.Random rand = new java.util.Random();
        long randomID = 1000000000L + rand.nextLong(9000000000L);
        this.messageID = String.valueOf(randomID);
        return this.messageID;
    }

    // Check if message ID is valid (max 10 characters)
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Check if recipient cell is valid (international code required)
    public String checkRecipientCell(String cell) {
        String cellWithoutPlus = cell.startsWith("+") ? cell.substring(1) : cell;

        if ((cell.startsWith("+27") || cell.startsWith("+")) && cellWithoutPlus.length() <= 12) {
            this.recipientCell = cell;
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Validate message length (max 250 characters)
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            this.messageText = message;
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Create message hash: first 2 digits of ID:message number:FIRST WORD:LAST WORD
    public String createMessageHash() {
        if (messageID == null || messageText == null) {
            return null;
        }

        String firstTwo = messageID.substring(0, 2);

        // Extract first and last words (remove punctuation)
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase().replaceAll("[^a-zA-Z]", "") : "";
        String lastWord = words.length > 0 ? words[words.length - 1].toUpperCase().replaceAll("[^a-zA-Z]", "") : "";

        this.messageHash = firstTwo + ":" + messageNumber + ":" + firstWord + ":" + lastWord;
        return this.messageHash;
    }

    // Send, store, or disregard message
    public String sentMessage(int userChoice) {
        switch (userChoice) {
            case 1:
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // Print all message details
    public String printMessage() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    // Return total messages sent
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Store message to JSON (placeholder)
    public String storeMessage() {
        return "Message successfully stored.";
    }

    // Getters and setters
    public void setMessageNumber(int num) {
        this.messageNumber = num;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }
    
        // Additional getters for JSON serialization
    public int getMessageNumber() {
        return messageNumber;
    }

    public int getTotalMessagesSent() {
        return totalMessagesSent;
    }

}
