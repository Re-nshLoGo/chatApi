package com.example.chatApp.dao;

import com.example.chatApp.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
    @Query(value = "select * from user_tbl where user_name = :name and status_id = 1", nativeQuery = true)
    public List<Users> findByUserName(String name);
    @Query(value = "select * from user_tbl where user_id = :id and status_id = 1", nativeQuery = true)
    public List<Users> getUsersByUserId(int id);
    @Query(value = "select * from user_tbl where status_id = 1", nativeQuery = true)
    public List<Users> getAllUsers();

    @Modifying
    @Transactional
    @Query(value = "update user_tbl set status_id = 2 where user_id = :id", countQuery = "select count(*) from user_tbl", nativeQuery = true)
    public void deleteUsersByUserId(int id);
//    @Query(value = "update user_tbl set status_id = 2 where user_id = :id", nativeQuery = true)
//    public Users deleteUsersByUserId(int id);


}
