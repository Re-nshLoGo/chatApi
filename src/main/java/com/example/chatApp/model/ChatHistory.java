package com.example.chatApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_tbl")
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;
    @ManyToOne
    private Users to;
    @ManyToOne
    private Users from;
    private String msg;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    @ManyToOne
    private Status status;

}
