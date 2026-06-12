
package com.mycompany.registration2;
import java.util.Random;


public class Message {
    
 // Variables to store message information
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;
 
    // Constructor - this runs when we create a new Message
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();       // auto-create ID
        this.messageHash = createMessageHash();     // auto-create hash
    }
 
    // ---------- Getters (so other classes can read these values) ----------
 
    public String getMessageID()     { return messageID; }
    public int    getMessageNumber() { return messageNumber; }
    public String getRecipient()     { return recipient; }
    public String getMessageText()   { return messageText; }
    public String getMessageHash()   { return messageHash; }
    public String getStatus()        { return status; }
 
    // ---------- Method 1: Generate a random 10-digit Message ID ----------
 
    private String generateMessageID() {
        Random rand = new Random();
        // nextInt(9000000000) gives 0-8999999999, adding 1000000000 makes it 10 digits
        long id = (long)(rand.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }
 
    // ---------- Method 2: checkMessageID ----------
    // Returns true if the message ID is exactly 10 characters long
 
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }
 
    // ---------- Method 3: checkRecipientCell ----------
    // Checks if the recipient number starts with + and is the right length
    // Returns a message string
 
    public String checkRecipientCell() {
        if (recipient.startsWith("+") && recipient.matches("^\\+\\d{1,3}\\d{1,10}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
    }
 
    // ---------- Method 4: createMessageHash ----------
    // Hash = first 2 digits of ID : message number : first word + last word (all caps)
    // Example: "00:1:HITONIGHT"
 
    public String createMessageHash() {
        // Get first 2 characters of the message ID
        String idStart = messageID.substring(0, 2);
 
        // Split the message into words
        String[] words = messageText.trim().split("\\s+");
 
        // Get first and last word, remove punctuation
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord  = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
 
        // Build and return the hash in uppercase
        String hash = idStart + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
 
    // ---------- Method 5: checkMessageLength ----------
    // Returns success or failure message based on length
 
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }
 
    // ---------- Method 6: sentMessage ----------
    // User picks 1 = Send, 2 = Disregard, 3 = Store
    // Sets the status and returns a result message
 
    public String sentMessage(int choice) {
        if (choice == 1) {
            status = "Sent";
            return "Message successfully sent.";
        } else if (choice == 2) {
            status = "Disregarded";
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            status = "Stored";
            return "Message successfully stored.";
        } else {
            return "Invalid option selected.";
        }
    }
 
    // ---------- Method 7: printMessages ----------
    // Returns all the message details as one String
 
    public String printMessages() {
        return "Message ID   : " + messageID    + "\n"
             + "Message Hash : " + messageHash  + "\n"
             + "Recipient    : " + recipient    + "\n"
             + "Message      : " + messageText;
    }
 
    // ---------- Method 8: returnTotalMessages ----------
    // Counts how many messages have been sent (status = "Sent")
    // Takes an array of messages and a count of how many are in it
 
    public static int returnTotalMessages(Message[] messages, int count) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (messages[i].getStatus().equals("Sent")) {
                total++;
            }
        }
        return total;
    }
}