package com.example.chatApp.service;

import com.example.chatApp.dao.StatusRepo;
import com.example.chatApp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;
    public int save(Status status1) {
      Status st = statusRepo.save(status1);
      return st.getStatusId();
    }
}
