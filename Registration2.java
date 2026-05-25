
package com.mycompany.registration2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registration2 {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        
        System.out.println("===== QuickChat - Registration =====");
        //Ask the user to enter their credintials
        System.out.println("First name: ");
        String name = myInput.nextLine();
        
        System.out.println("Last name: ");
        String lastName = myInput.nextLine();
        
        //Ask the user to enter username with ertain resrictions
        System.out.println("Enter a username");
        String userName = myInput.nextLine();
        
        if(userName.contains("_")&& userName.length() <=5){
            System.out.println("Username has been succefully captured");
        }else{
            System.out.println("Username is not correctly formatted; please ensure that your username cantains "
                    + "an underscore and is n more than five characters in length.");
        }
        
        //May you please enter password
        System.out.println("Enter password: ");
        String password = myInput.nextLine();
        
        if(password != null 
                && password.matches(".*[0-9].*") 
                && password.length() >= 8 
                && password.matches(".*[!@#$%].*") 
                && password.matches(".*[A-Z].*")) 
        {
            System.out.println("Password succesfully captured");
        }else{
            System.out.println("Password is not correctly formatted; please ensure that your password cantains "
                    + "at lest eight characters, a capital letter, a number, and a special character ");
        }
        
        //Ask the user to enter their ceelphone numbers
        System.out.println("Enter cellphone number");
        String number = myInput.nextLine();
        
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        
        if(number != null && matcher.matches() && number.length() ==12){
            System.out.println("Cellphone number succesfully added");
        }else{
            System.out.println("Cellphone number incorrectly formatted or does not contain international code");
        }
        
        if(userName.contains("_") && userName.length() <=5 && password != null 
                && password.length() >=8
                && password.matches(".*[0-9].*") 
                && password.matches(".*[!@#$%].")){
            System.out.println(" Welcome " + name + " " + lastName + " " + "it is great to see you.");
        }else{
            System.out.println("Username or password incorrect,please try again.");
        

        // ================================================================
        // PART 2 - QUICKCHAT MENU
        // Only reaches here if login was successful
        // ================================================================
        Scanner input = new Scanner(System.in);
            System.out.println("\nWelcome to QuickChat.");
 
        // Ask how many messages the user wants to send
            System.out.println("How many messages would you like to send this session?");
        int maxMessages = Integer.parseInt(input.nextLine().trim());
 
        // Array to store all messages this session
        Message[] sessionMessages = new Message[maxMessages];
        int messageCount = 0;
 
        int menuChoice = 0;
 
        // WHILE LOOP - keeps the menu running until the user quits
        while (menuChoice != 3) {
 
            System.out.println("\n===== Menu =====");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("Choose an option:");
 
            menuChoice = Integer.parseInt(input.nextLine().trim());
 
            if (menuChoice == 1) {
 
                // FOR LOOP - only lets user send the set number of messages
                for (int i = 0; i < maxMessages; i++) {
 
                    int msgNum = i + 1;
                    System.out.println("\n----- Message " + msgNum + " of " + maxMessages + " -----");
 
                    // Get recipient number
                    System.out.println("Enter recipient cell number (e.g. +27718693002):");
                    String recipient = input.nextLine();
 
                    // Get message text
                    System.out.println("Enter your message (max 250 characters):");
                    String messageText = input.nextLine();
 
                    // Create a new Message object
                    Message msg = new Message(msgNum, recipient, messageText);
 
                    // Check recipient number
                    System.out.println(msg.checkRecipientCell());
 
                    // Check message length - if too long, ask user to re-enter
                    String lengthResult = msg.checkMessageLength();
                    if (!lengthResult.equals("Message ready to send.")) {
                        System.out.println(lengthResult);
                        System.out.println("Please re-enter a shorter message:");
                        messageText = input.nextLine();
                        msg = new Message(msgNum, recipient, messageText);
                    }
                    System.out.println("Message ready to send.");
 
                    // Show auto-generated details
                    System.out.println("Message ID   : " + msg.getMessageID());
                    System.out.println("Message Hash : " + msg.getMessageHash());
 
                    // Ask what to do with the message
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.println("Choose an option:");
 
                    int sendChoice = Integer.parseInt(input.nextLine().trim());
 
                    // Set status and show result
                    String sendResult = msg.sentMessage(sendChoice);
                    System.out.println(sendResult);
 
                    // Save to session array
                    sessionMessages[messageCount] = msg;
                    messageCount++;
 
                    // Show full details if sent or stored
                    if (msg.getStatus().equals("Sent") || msg.getStatus().equals("Stored")) {
                        System.out.println("\n--- Full Message Details ---");
                        System.out.println(msg.printMessages());
                    }
                }
 
                // Show total sent after all messages are done
                int totalSent = Message.returnTotalMessages(sessionMessages, messageCount);
                System.out.println("\nTotal messages sent: " + totalSent);
 
            } else if (menuChoice == 2) {
                System.out.println("Coming Soon.");
 
            } else if (menuChoice == 3) {
                System.out.println("Goodbye! Thanks for using QuickChat.");
 
            } else {
                System.out.println("Please enter 1, 2, or 3.");
            }
        } 
    }
    }
}


    

