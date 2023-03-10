package com.example.chatApp.dao;

import com.example.chatApp.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatHistoryRepo extends JpaRepository<ChatHistory,Integer> {

    @Query(value = "select * from chat_tbl where from_user_id = :id and status_status_id = 1",nativeQuery = true)
    public List<ChatHistory> getChatsByUserId(int id);

    @Query(value = "select * from chat_tbl where (from_user_id = :senderId and to_user_id = :recId)" +
            "or (from_user_id = :recId and to_user_id = :senderId) and status_status_id = 1 order by created_date",nativeQuery = true)
    List<ChatHistory> getConversation(int senderId, int recId);
}
