package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll(TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.tt = new TransactionController(messageController, idController);

    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?

//        ServerController serverController = ServerController.shared();
//        serverController.idGet();
//        serverController.messageGet();
//
//        MessageController messageController = MessageController.shared();
//        System.out.println(messageController.messagesSeen);

        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/post"));
    }

    public String get_ids()  throws JsonProcessingException {
        List<Id> ids = tt.getIds();
        String show = "";
        for (Id i : ids) {
            IdTextView view = new IdTextView(i);
            show += view + "\n";
        }
        return show; //tt.makecall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }


    }

