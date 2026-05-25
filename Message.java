/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MBATHA
 */
public class Message {
    
    public Message() {
    }
     // ----------------------------------------------------------------
    // Test 1: Message length — Success (within 250 characters)
    // Test data: Message 1 from spec
    // ----------------------------------------------------------------
    @Test
    public void testCheckMessageLengthSuccess() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message ready to send.";
        String actual   = msg.checkMessageLength();
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------
    // Test 2: Message length — Failure (exceeds 250 characters)
    // ----------------------------------------------------------------
    @Test
    public void testCheckMessageLengthFailure() {
        // Build a 260-character message
        String longMsg = "A".repeat(260);
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(1, "+27718693002", longMsg);
        String result = msg.checkMessageLength();
        assertTrue(result.contains("Message exceeds 250 characters by 10"));
    }

    // ----------------------------------------------------------------
    // Test 3: Recipient cell — Success
    // Test data: +27718693002 (Message 1)
    // ----------------------------------------------------------------
    @Test
    public void testCheckRecipientCellSuccess() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expected = "Cell phone number successfully captured.";
        String actual   = msg.checkRecipientCell();
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------
    // Test 4: Recipient cell — Failure
    // Test data: 08575975889 (Message 2 — no international code)
    // ----------------------------------------------------------------
    @Test
    public void testCheckRecipientCellFailure() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        String expected = "Cell phone number is incorrectly formatted or does not contain "
                        + "an international code. Please correct the number and try again.";
        String actual   = msg.checkRecipientCell();
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------
    // Test 5: Message Hash is correct
    // Test data: Message 1 — "Hi Mike, can you join us for dinner tonight?"
    // Expected hash format: first 2 of ID : msgNum : FIRSTWORDLASTWORD
    // The spec example for this data = "00:0:HITONIGHT" (first 2 digits of ID vary,
    // but the word-portion "HITONIGHT" must match)
    // We test that the hash ends with ":HITONIGHT" and has the correct structure.
    // ----------------------------------------------------------------
    @Test
    public void testCreateMessageHashMessage1() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.getMessageHash();

        // Hash must contain ":1:HITONIGHT" (message number is 1)
        assertTrue(hash.endsWith(":1:HITONIGHT"),
            "Expected hash to end with ':1:HITONIGHT' but got: " + hash);
    }

    // ----------------------------------------------------------------
    // Test 6: Message Hash for Message 2
    // Test data: "Hi Keegan, did you receive the payment?"
    // Expected word portion: HIPAYMENT
    // ----------------------------------------------------------------
    @Test
    public void testCreateMessageHashMessage2() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        String hash = msg.getMessageHash();

        assertTrue(hash.endsWith(":2:HIPAYMENT"),
            "Expected hash to end with ':2:HIPAYMENT' but got: " + hash);
    }

    // ----------------------------------------------------------------
    // Test 7: Message ID is generated and exactly 10 characters
    // ----------------------------------------------------------------
    @Test
    public void testCheckMessageID() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID(), "Message ID should be exactly 10 characters.");
        System.out.println("Message ID generated: " + msg.getMessageID());
    }

    // ----------------------------------------------------------------
    // Test 8: sentMessage — User selects Send
    // ----------------------------------------------------------------
    @Test
    public void testSentMessageSend() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message successfully sent.";
        String actual   = msg.sentMessage(1);
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------
    // Test 9: sentMessage — User selects Disregard
    // ----------------------------------------------------------------
    @Test
    public void testSentMessageDisregard() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        String expected = "Press 0 to delete the message.";
        String actual   = msg.sentMessage(2);
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------
    // Test 10: sentMessage — User selects Store
    // ----------------------------------------------------------------
    @Test
    public void testSentMessageStore() {
        com.mycompany.registration2.Message msg = new com.mycompany.registration2.Message(3, "+27718693002", "Test stored message.");
        String expected = "Message successfully stored.";
        String actual   = msg.sentMessage(3);
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------
    // Test 11: returnTotalMessages — counts only "Sent" messages
    // ----------------------------------------------------------------
    @Test
    public void testReturnTotalMessages() {
        java.util.List<com.mycompany.registration2.Message> list = new java.util.ArrayList<>();

        com.mycompany.registration2.Message msg1 = new com.mycompany.registration2.Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg1.sentMessage(1); // Sent

        com.mycompany.registration2.Message msg2 = new com.mycompany.registration2.Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        msg2.sentMessage(2); // Disregarded

        list.add(msg1);
        list.add(msg2);

        int total = com.mycompany.registration2.Message.returnTotalMessages(list);
        assertEquals(1, total, "Only 1 message should be counted as sent.");
    }
    
}
