package com.example.chatApp.dao;

import com.example.chatApp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<Status,Integer> {
}
