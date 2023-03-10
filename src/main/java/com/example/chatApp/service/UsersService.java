package com.example.chatApp.service;

import com.example.chatApp.dao.UsersRepo;

import java.sql.Timestamp;
import java.util.List;
import com.example.chatApp.model.Users;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UsersRepo usersRepo;

    public int add(Users users) {
       Users users1 =  usersRepo.save(users);
       return users1.getUserId();
    }
    public JSONArray getUsers(String id){
        JSONArray response = new JSONArray();
        if(id != null){
            List<Users> usersList = usersRepo.getUsersByUserId(Integer.valueOf(id));
            for (Users user:usersList) {
                JSONObject json = createResponse(user);
                response.put(json);
            }
        }else{
            List<Users> usersList = usersRepo.getAllUsers();
            for (Users user:usersList) {
                JSONObject json = createResponse(user);
                response.put(json);
            }
        }
        return response;
    }

    private JSONObject createResponse(Users user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",user.getUserId());
        jsonObject.put("username",user.getUsername());
        jsonObject.put("firstname",user.getFirstName());
        jsonObject.put("lastname",user.getLastName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("gender",user.getGender());
        jsonObject.put("createddate",user.getCreatedDate());
        return jsonObject;
    }

    public void delete(String id) {
        usersRepo.deleteUsersByUserId(Integer.valueOf(id));
//        usersRepo.deleteById(Integer.valueOf(id));
    }

    public JSONObject login(String username, String password) {
        JSONObject response = new JSONObject();
        List<Users> usersList = usersRepo.findByUserName(username);
        if(usersList.isEmpty()){
            response.put("errorMessage","Username doesn't exist");
            return response;
        }else{
            Users userobj = usersList.get(0);
            if(password.equals(userobj.getPass())){
               response = createResponse(userobj);
            }else{
               response.put("password","password is not valid");
            }
        }
        return response;
    }

    public JSONObject updateUser(Users newusers,String userId) {
        List<Users> usersList = usersRepo.getUsersByUserId(Integer.valueOf(userId));
        JSONObject errorobj = new JSONObject();
        if(!usersList.isEmpty()){
            Users olduser = usersList.get(0);
            olduser.setUserId(newusers.getUserId());
            Timestamp updatetime = new Timestamp(System.currentTimeMillis());
            olduser.setUpdatedDate(updatetime);
            usersRepo.save(olduser);
        }else{
            errorobj.put("errorMessage","User doesn't exist");
        }
        return errorobj;

    }
}
