package com.example.chatApp.service;

import com.example.chatApp.dao.ChatHistoryRepo;
import com.example.chatApp.model.ChatHistory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatHistoryRepo chatRepo;

    public void sendMsg(ChatHistory chat) {
     chatRepo.save(chat);
    }

    public JSONObject getChat(int id) {
        List<ChatHistory> chatHistoryList = chatRepo.getChatsByUserId(id);
        JSONObject response = new JSONObject();
        if(!chatHistoryList.isEmpty()){
           response.put("senderId",chatHistoryList.get(0).getFrom().getUserId());
           response.put("senderUserName",chatHistoryList.get(0).getFrom().getUsername());
           response.put("sending-Date",chatHistoryList.get(0).getFrom().getCreatedDate());
        }
        JSONArray receivers = new JSONArray();
        for (ChatHistory chats:chatHistoryList) {
           JSONObject json = new JSONObject();
           json.put("receiverId",chats.getTo().getUserId());
           json.put("receiverName",chats.getTo().getFirstName());
           json.put("Message",chats.getMsg());
           receivers.put(json);
        }
        response.put("Receivers",receivers);
        return response;
    }

    public JSONObject getConver(int senderId, int recId) {
        List<ChatHistory> chatHistories = chatRepo.getConversation(senderId,recId);
        JSONObject response = new JSONObject();
        if(!chatHistories.isEmpty()) {
            response.put("senderId",chatHistories.get(0).getFrom().getUserId());
            response.put("receiverId",chatHistories.get(0).getTo().getUserId());
        }
        JSONArray array = new JSONArray();
        for(ChatHistory chats : chatHistories){
            JSONObject ans = new JSONObject();
            ans.put("senderName",chats.getFrom().getFirstName());
            ans.put("receiverName",chats.getTo().getFirstName());
            ans.put("message",chats.getMsg());
            array.put(ans);
        }
        response.put("conversation",array);
        return response;
    }
}
