package com.example.chatApp.controller;

import com.example.chatApp.model.Status;
import com.example.chatApp.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @Autowired
    StatusService statusService;
    @PostMapping("save-status")
    public ResponseEntity<String> createStatus(@RequestBody String status){
        Status status1 = setStatus(status);
        int id = statusService.save(status1);
        return new ResponseEntity<>("saved status id "+id, HttpStatus.CREATED);
    }

    private Status setStatus(String status1) {
        Status status = new Status();
        JSONObject json = new JSONObject(status1);
        status.setName(json.getString("name"));
        status.setDescription(json.getString("description"));
        return status;
    }
}
