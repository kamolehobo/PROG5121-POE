package com.mycompany.poe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JSONUtil - Handles storing messages to JSON file
 * Uses Google Gson library for JSON serialization
 * Attribution: Google Gson (https://github.com/google/gson)
 */
public class JSONUtil {

    private static final String FILE_NAME = "messages.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Saves a list of messages to a JSON file
     * @param messages - ArrayList of Message objects to save
     * @return true if successful, false otherwise
     */
    public static boolean saveMessagesToJSON(ArrayList<Message> messages) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(messages, writer);
            System.out.println("Messages successfully saved to " + FILE_NAME);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving messages to JSON: " + e.getMessage());
            return false;
        }
    }

    /**
     * Converts a single message to JSON string
     * @param message - Message object to convert
     * @return JSON string representation of the message
     */
    public static String messageToJSON(Message message) {
        return gson.toJson(message);
    }
}
