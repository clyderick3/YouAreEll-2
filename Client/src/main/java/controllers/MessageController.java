package controllers;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class MessageController {

    public HashSet<Message> messagesSeen = new HashSet<>(); //TODO change public back to private after testing.
    // why a HashSet??

    private MessageController(){
        ServerController serverController = ServerController.shared();
        JSONArray messagesJSON = serverController.messageGet();
//        Message[] messages = (Message[]) messagesJSON.toArray(new Message[0]);
//        for (int i = 0; i < messagesJSON.size(); i++) {
//            messagesSeen.add(messages[i]);
//        }
        for (int i = 0; i<messagesJSON.size(); i++) {
            Object object = messagesJSON.get(i);
            String string = object.toString();
            String[] messageList = string.split(",");
            String toID = messageList[0].substring(8);
            String sequence = messageList[1].substring(11);
            String message = messageList[2].substring(10);
            String fromID = messageList[3].substring(9);
            String timestamp = messageList[4].substring(12);
            Message messageObject = new Message(message,fromID,toID,timestamp,sequence);
            messagesSeen.add(messageObject);
        }
    }

    private static MessageController myController = new MessageController();

    public static MessageController shared() {
        return myController;
    }

    public ArrayList<Message> getMessages() {
        Message[] array = messagesSeen.toArray(new Message[0]);
        ArrayList<Message> messages = new ArrayList<>();
        for (int i =0; i<21; i++){
            messages.add(array[i]);
        }
//        messages.stream()
        ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
        ArrayList<Message> mostRecent = new ArrayList<>();
        for(int i = 2; i > 0;  i--){
            mostRecent.add(copy.get(i));
        }
        mostRecent.forEach(System.out::println);
        return mostRecent;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}