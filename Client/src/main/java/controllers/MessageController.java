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

    private ServerController serverController = ServerController.shared();


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
                ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
                ArrayList<Message> mostRecent = new ArrayList<>();
        for(int i = copy.size()-1; i > copy.size()-21; i--){
            mostRecent.add(copy.get(i));
        }
        mostRecent.forEach(System.out::println);
        return mostRecent;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getFromId().equals(Id.getGithub())) {
                messages.add(m);
            }
        }
        return messages;
    }

    public Message getMessageForSequence(String seq) {
        for (Message m : messagesSeen) {
            if (m.getSeqId().equals(seq)){
                return m;
            }
        }
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        ArrayList<Message> msgFromFriend = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getFromId().equals(friendId.getGitHub()) && m.getToId().equals(myId.getGithub())){
                msgFromFriend.add(m);
            }
        }
        return msgFromFriend;
    }

    public Message postMessage(Id myId, Id toId, Message msg) throws JsonProcessingException  {
        Message msgToSend = new Message("mrising", "mrising", "uh Clyde, you there?");
        ServerController.shared().messagePost(msgToSend);
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
//    }