package controllers;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class MessageController {

    private HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??
    private final ServerController serverController = ServerController.shared();


    public MessageController() throws JsonProcessingException {
        String messageString = String.valueOf(serverController.messageGet());
        ObjectMapper objectMapper = new ObjectMapper();
        messagesSeen = objectMapper.readValue(messageString, new TypeReference<>(){});
        }

    public ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message i : messagesSeen) {
            messages.add(i);
        }
        return messages;
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


//        ServerController serverController = ServerController.shared();
//        JSONArray messagesJSON = serverController.messageGet();
//
//        for (int i = 0; i<messagesJSON.size(); i++) {
//            Object object = messagesJSON.get(i);
//            String string = object.toString();
//            String[] messageList = string.split(",");
//            String toID = messageList[0].substring(8);
//            String sequence = messageList[1].substring(11);
//            String message = messageList[2].substring(10);
//            String fromID = messageList[3].substring(9);
//            String timestamp = messageList[4].substring(12);
//            Message messageObject = new Message(message,fromID,toID,timestamp,sequence);
//            messagesSeen.add(messageObject);
//        }
//    }
//
//    private static MessageController myController = new MessageController();
//
//    public static MessageController shared() {
//        return myController;
//    }
//
//        ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
//                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
//        ArrayList<Message> mostRecent = new ArrayList<>();
//        for(int i = copy.size()-1; i > copy.size()-21; i--){
//            mostRecent.add(copy.get(i));
//        }
//        mostRecent.forEach(System.out::println);
//        return mostRecent;
//    }