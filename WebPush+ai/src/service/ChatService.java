package service;

import Repository.ChatDA;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class ChatService {
    private ChatService() {
    }

    public static ChatService chatService = new ChatService();

    public static ChatService getInstance() {
        return chatService;
    }

    public void save(ArrayList<String> users, ArrayList<String> message){
        ChatDA chatDA = new ChatDA();
        chatDA.insert(users, message);
    }

    public JSONArray selectAll(){
        ChatDA chatDA = new ChatDA();
        return chatDA.selectAll();
    }

}
