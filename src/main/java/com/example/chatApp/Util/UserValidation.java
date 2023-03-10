package com.example.chatApp.Util;

import com.example.chatApp.dao.StatusRepo;
import com.example.chatApp.dao.UsersRepo;
import com.example.chatApp.model.Status;
import com.example.chatApp.model.Users;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
@Component
public class UserValidation {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    StatusRepo statusRepo;
    public JSONObject validateUser(String userdata) {

        JSONObject json = new JSONObject(userdata);
        JSONObject errorList = new JSONObject();
        if(json.has("username")){
            String userName = json.getString("username");
            List<Users> userslist = usersRepo.findByUserName(userName);
            if(!userslist.isEmpty()){
                errorList.put("username","username already exist");
            }
        }else{
            errorList.put("username","Missing username");

        }
        if(json.has("firstname")){

            String userName = json.getString("username");
        }else {
            errorList.put("firstname","Missing firstname");

        }
        if(json.has("email")){

            String email = json.getString("email");
            if (!CommonUtils.isvalidemail(email)){
                errorList.put("email","please enter a valid email eg : abcd@gmail.com");

            }
        }else{
            errorList.put("email","Missing email");

        }
        if(json.has("password")){

            String password = json.getString("password");
            if(!CommonUtils.isvalidPassword(password)){
                errorList.put("password","please enter a valid password should be max 8 characters eg : adhR@#1234");
            }
        }else{
            errorList.put("password","Missing password");

        }
        if(json.has("phonenum")){

            String phoneNum = json.getString("phonenum");
            if (!CommonUtils.isvalidPhonenum(phoneNum)){
                errorList.put("phoneNum","please enter a valid phone num");
            }
        }else{
            errorList.put("phoneNum","Missing phoneNum");

        }
        if(json.has("age")){

            String age = json.getString("age");
        }

        return errorList;

    }
    public Users setuser(String userdata) {
        Users users = new Users();
        JSONObject json = new JSONObject(userdata);
        users.setUsername(json.getString("username"));
        users.setFirstName(json.getString("firstname"));
        users.setEmail(json.getString("email"));
        users.setPass(json.getString("password"));
        users.setPhoneNum(json.getString("phonenum"));
        if(json.has("age")){
            users.setAge(json.getInt("age"));
        }
        if(json.has("lastname")){
            users.setLastName(json.getString("lastname"));
        }
        if(json.has("gender")){
            users.setGender(json.getString("gender"));
        }
        Status status = statusRepo.findById(1).get();
        users.setStatusId(status);
        Timestamp createdtime = new Timestamp(System.currentTimeMillis());
        users.setCreatedDate(createdtime);
        return users;
    }
    public JSONObject validatLogin(JSONObject requestdata) {
        JSONObject error = new JSONObject();
        if(!requestdata.has("username")){
            error.put("username","Missing parameter");
        }

        if(!requestdata.has("password")){
            error.put("password","Missing parameter");
        }
        return error;
    }

}
