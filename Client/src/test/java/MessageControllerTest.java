import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.ServerController;
import org.json.simple.JSONArray;
import org.junit.Test;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MessageControllerTest {

    @Test
    public void name() {
    }

    @Test
    public void MessageControllerTest() {
    }

    //why don't you have @Test anywhere. Why is the test a class?

    public void getMessagesTest() throws IOException {
        List<String> messageStr = new ArrayList<String>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray messages = ServerController.shared().messageGet();
        messageStr.addAll(messages);
        Map<String,String> map = objectMapper.readValue((DataInput) messageStr, Map.class);
        System.out.println(map.size());
        Logger.getLogger(String.valueOf(messageStr));
    }

}
