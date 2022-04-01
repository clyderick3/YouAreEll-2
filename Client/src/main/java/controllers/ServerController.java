package controllers;

import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerController<JsonString> {
    private String rootURL = "http://zipcode.rocks:8085";
    HttpURLConnection connection;
    private static ServerController svr = new ServerController();

    private ServerController() {
    }

    public static ServerController shared() {
        return svr;
    }

    public JSONArray idGet() {
        BufferedReader reader;
        JSONParser jsonParser = new JSONParser();
        JSONArray ids = null;
        try {
            URL url = new URL(rootURL + "/ids");
            connection = (HttpURLConnection) url.openConnection();
            //Request Method
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader((new InputStreamReader(connection.getErrorStream())));
                Object obj = jsonParser.parse(reader);
                ids = (JSONArray) obj;
                System.out.println(ids);
                reader.close();
            } else {
                reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                Object obj = jsonParser.parse(reader);
                ids = (JSONArray) obj;
                System.out.println(ids);
                reader.close();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return ids;
    }

    public JSONArray messageGet() {
        BufferedReader reader;
        JSONParser jsonParser = new JSONParser();
        JSONArray messages = null;
        try {
            URL url = new URL(rootURL + "/messages");
            connection = (HttpURLConnection) url.openConnection();
            //Request Method
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader((new InputStreamReader(connection.getErrorStream())));
                Object obj = jsonParser.parse(reader);
                messages = (JSONArray) obj;
                System.out.println(messages);
                reader.close();
            } else {
                reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                Object obj = jsonParser.parse(reader);
                messages = (JSONArray) obj;
                System.out.println(messages);
                reader.close();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return messages;
    }
}