package com.mycompany.poe;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message message = new Message();
        String result = message.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        Message message = new Message();
        String longMessage = "a".repeat(300);
        String result = message.checkMessageLength(longMessage);
        assertTrue(result.contains("exceeds 250 characters by"));
    }

    @Test
    public void testRecipientCellSuccess() {
        Message message = new Message();
        String result = message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellFailure() {
        Message message = new Message();
        String result = message.checkRecipientCell("08575975889");
        assertTrue(result.contains("incorrectly formatted"));
    }

    @Test
    public void testMessageIDGeneration() {
        Message message = new Message();
        message.generateMessageID();
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testCreateMessageHash() {
        Message message = new Message();
        message.generateMessageID();
        message.setMessageNumber(0);
        message.checkRecipientCell("+27718693002");
        message.checkMessageLength("Hi Mike, can you join us for dinner tonight?");

        String hash = message.createMessageHash();
        assertTrue(hash.contains(":"));
        assertTrue(hash.contains("HI"));
        assertTrue(hash.contains("TONIGHT"));
    }

    @Test
    public void testSendMessage() {
        Message message = new Message();
        String result = message.sentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message();
        String result = message.sentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message();
        String result = message.sentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testReturnTotalMessages() {
        Message message = new Message();
        int initialTotal = message.returnTotalMessages();
        assertEquals(0, initialTotal);
        
        message.sentMessage(1);
        int total = message.returnTotalMessages();
        assertEquals(1, total);
    }
}
