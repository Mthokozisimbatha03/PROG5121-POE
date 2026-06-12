package com.mycompany.registration2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MBATHA
 */
public class Messagemangertest {
  MessageManager manager = new MessageManager();
  
  public Messagemangertest() {
  }
  
  @Test
public void testSentMessagesArrayPopulated() {

    manager.addSentMessage(
            "1111111111",
            "11:1:GETCAKE",
            "+27834557896",
            "Did you get the cake?"
    );

    manager.addSentMessage(
            "2222222222",
            "22:2:DINNERTIME",
            "+27838884567",
            "It is dinner time!"
    );

    assertEquals(
            "Did you get the cake?",
            manager.searchMessageID("1111111111")
    );

    assertEquals(
            "It is dinner time!",
            manager.searchMessageID("2222222222")
    );
}

@Test
public void testDisplayLongestMessage() {

    MessageManager manager = new MessageManager();

    manager.addStoredMessage(
            "Did you get the cake?"
    );

    manager.addStoredMessage(
            "Where are you? You are late! I have asked you to be on time."
    );

    manager.addStoredMessage(
            "Yohoooo, I am at your gate."
    );

    assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            manager.displayLongestStoredMessage()
    );
}

@Test
public void testSearchMessageID() {

    MessageManager manager = new MessageManager();

    manager.addSentMessage(
            "0838884567",
            "08:4:DINNERTIME",
            "+27838884567",
            "It is dinner time!"
    );

    assertEquals(
            "It is dinner time!",
            manager.searchMessageID("0838884567")
    );
}

@Test
public void testSearchRecipient() {

    MessageManager manager = new MessageManager();

    manager.addSentMessage(
            "1111111111",
            "11:2:WHERETIME",
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    manager.addSentMessage(
            "2222222222",
            "22:5:LEAVINGYOU",
            "+27838884567",
            "Ok, I am leaving without you."
    );

    String result =
            manager.searchRecipient(
                    "+27838884567"
            );

    assertTrue(
            result.contains(
                    "Where are you? You are late! I have asked you to be on time."
            )
    );

    assertTrue(
            result.contains(
                    "Ok, I am leaving without you."
            )
    );
}

@Test
public void testDeleteMessageUsingHash() {

    MessageManager manager = new MessageManager();

    manager.addSentMessage(
            "1111111111",
            "11:2:WHERETIME",
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    assertEquals(
            "Where are you? You are late! I have asked you to be on time. successfully deleted.",
            manager.deleteByHash(
                    "11:2:WHERETIME"
            )
    );
}

@Test
public void testMessageNotFound() {

    MessageManager manager = new MessageManager();

    assertEquals(
            "Message not found.",
            manager.searchMessageID(
                    "9999999999"
            )
    );
}

@Test
public void testHashNotFound() {

    MessageManager manager = new MessageManager();

    assertEquals(
            "Hash not found.",
            manager.deleteByHash(
                    "INVALIDHASH"
            )
    );
}

@Test
public void testStoredMessageArray() {

    MessageManager manager = new MessageManager();

    manager.addStoredMessage(
            "Where are you? You are late! I have asked you to be on time."
    );

    assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            manager.displayLongestStoredMessage()
    );
}
}
