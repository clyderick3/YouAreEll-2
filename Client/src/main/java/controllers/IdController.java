package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    private HashMap<String, Id> allIds = new HashMap<>();

    Id myId;

    public ArrayList<Id> getIds() throws JsonProcessingException {
        ServerController serverController = ServerController.shared();
        String ids = String.valueOf(serverController.idGet());
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> listIds = objectMapper.readValue(ids, new TypeReference<ArrayList<Id>>() {
        });

        for (Id i : listIds) {
            allIds.put(i.getGithub(), i);
        }
        return listIds;
    }
//        ArrayList<Id> ids = new ArrayList<>();
//        ServerController serverController = ServerController.shared();
//        ids.addAll(serverController.idGet());
//        System.out.println(ids);

//    public Id postId(Id id) throws JsonProcessingException {
//        // create json from id
//        id = new Id("test","test2");
//        ServerController.shared().idPost(id);
//        // call server, get json result Or error
//        // result json to Id obj
//
//        return null;
//    }
//
//    public Id putId(Id id) {
//        return null;
//    }

}