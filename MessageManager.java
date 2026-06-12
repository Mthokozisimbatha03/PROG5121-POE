package com.mycompany.registration2;
import java.util.ArrayList;


public class MessageManager {

    ArrayList<String> sentMessages = new ArrayList<>();
    ArrayList<String> disregardedMessages = new ArrayList<>();
    ArrayList<String> storedMessages = new ArrayList<>();

    ArrayList<String> messageHashes = new ArrayList<>();
    ArrayList<String> messageIDs = new ArrayList<>();

    public void addSentMessage(String id, String hash, String message){

        sentMessages.add(message);
        messageIDs.add(id);
        messageHashes.add(hash);
    }

    public void addStoredMessage(String message){

        storedMessages.add(message);
    }

    public void addDisregardedMessage(String message){

        disregardedMessages.add(message);
    }

    public String displayLongestStoredMessage(){

        if(storedMessages.isEmpty()){
            return "No stored messages.";
        }

        String longest = storedMessages.get(0);

        for(String msg : storedMessages){

            if(msg.length() > longest.length()){
                longest = msg;
            }
        }

        return longest;
    }

    public String searchByMessageID(String id){

        for(int i = 0; i < messageIDs.size(); i++){

            if(messageIDs.get(i).equals(id)){
                return sentMessages.get(i);
            }
        }

        return "Message not found.";
    }

    public String deleteByHash(String hash){

        for(int i = 0; i < messageHashes.size(); i++){

            if(messageHashes.get(i).equals(hash)){

                String deleted = sentMessages.get(i);

                sentMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);

                return deleted + " successfully deleted.";
            }
        }

        return "Hash not found.";
    }

    public void displayReport(){

        System.out.println("\n===== MESSAGE REPORT =====");

        for(int i = 0; i < sentMessages.size(); i++){

            System.out.println("Message Hash: "
                    + messageHashes.get(i));

            System.out.println("Message ID: "
                    + messageIDs.get(i));

            System.out.println("Message: "
                    + sentMessages.get(i));

            System.out.println("-------------------");
        }
    }
}