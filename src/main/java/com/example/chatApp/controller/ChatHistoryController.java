package com.example.chatApp.controller;

import com.example.chatApp.dao.StatusRepo;
import com.example.chatApp.dao.UsersRepo;
import com.example.chatApp.model.ChatHistory;
import com.example.chatApp.model.Status;
import com.example.chatApp.model.Users;
import com.example.chatApp.service.ChatService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("api/v1/chat")
public class ChatHistoryController {
    @Autowired
    ChatService chatService;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    StatusRepo statusRepo;
    @PostMapping("/send-message")
    public ResponseEntity<String> sendmsg(@RequestBody String requestdata){
        JSONObject jsonObject = new JSONObject(requestdata);
        JSONObject isvalidrequest = validateData(jsonObject);
        ChatHistory chatHistory = setChatHistory(jsonObject);
        if(isvalidrequest.isEmpty()){
          chatService.sendMsg(chatHistory);
          return new ResponseEntity<>("Message sent", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Message not sent", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/get-chat")
    public ResponseEntity<String> getchatHistory( @RequestParam int userid){
        JSONObject ansObject = chatService.getChat(userid);
        return new ResponseEntity<>(ansObject.toString(),HttpStatus.OK);
    }
    @GetMapping("/get-conversation")
    public ResponseEntity<String> getconversation(@RequestParam int senderId,@RequestParam int recId){
        JSONObject ans = chatService.getConver(senderId,recId);
        return new ResponseEntity<>(ans.toString(),HttpStatus.OK);
    }


     private JSONObject validateData(JSONObject jsonObject) {
            JSONObject errorobj = new JSONObject();
            if(!jsonObject.has("sender")){
                errorobj.put("sender","Missing parameter");
            }
            if(!jsonObject.has("receiver")){
                errorobj.put("receiver","Missing parameter");
            }
            if(jsonObject.has("message")){
                String msg = jsonObject.getString("message");
                if(msg.isEmpty() || msg.isBlank()){
                    errorobj.put("message","please provide message body");
                }
            }else {
                errorobj.put("message","Missing parameter");
            }
            return errorobj;
        }

    private ChatHistory setChatHistory(JSONObject jsonObject) {
        ChatHistory chat = new ChatHistory();
        int sender = jsonObject.getInt("sender");
        int receiver = jsonObject.getInt("receiver");
        String msg = jsonObject.getString("message");
        Users senderObj = usersRepo.getById(sender);
        Users receiverObj = usersRepo.getById(receiver);
        Status status = statusRepo.findById(1).get();
        chat.setStatus(status);
        chat.setFrom(senderObj);
        chat.setTo(receiverObj);
        chat.setMsg(msg);
        Timestamp createdtime = new Timestamp(System.currentTimeMillis());
        chat.setCreatedDate(createdtime);
        return chat;
    }
}
