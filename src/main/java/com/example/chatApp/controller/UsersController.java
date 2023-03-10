package com.example.chatApp.controller;

import com.example.chatApp.Util.UserValidation;
import com.example.chatApp.model.Users;
import com.example.chatApp.service.UsersService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
@RestController
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    UserValidation validateUser;
    @PostMapping("/add-user")
    public ResponseEntity<String> createUser(@RequestBody String userdata){
        JSONObject isvalid = validateUser.validateUser(userdata);
        Users users = null;
        if(isvalid.isEmpty()){
            users = validateUser.setuser(userdata);
            int id =  usersService.add(users);
            return new ResponseEntity<>("saved user with id "+id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(isvalid.toString(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-users")
    public JSONArray getusers(@Nullable @RequestParam String id){
        JSONArray useraaray = usersService.getUsers(id);
        return useraaray;
    }
    @DeleteMapping(value = "/delete-user/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        usersService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
    }
    @PutMapping("update-user/{userid}")
    public ResponseEntity<String> userUpdate(@RequestBody String userdata,@PathVariable String userId){
        JSONObject isvalid = validateUser.validateUser(userdata);
        Users users = null;
        if(isvalid.isEmpty()){
            users = validateUser.setuser(userdata);
            JSONObject responseobj = usersService.updateUser(users, userId);
            if(responseobj.has("errorMessage")){
                return new ResponseEntity<>(responseobj.toString(),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(responseobj.toString(),HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(isvalid.toString(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(String requestdata){
        JSONObject  requestjson = new JSONObject(requestdata);
        JSONObject isvalidlogin = validateUser.validatLogin(requestjson);

        if(isvalidlogin.isEmpty()){
          String username = requestjson.getString("username");
          String password = requestjson.getString("password");
            JSONObject responseobj =  usersService.login(username,password);
            if(responseobj.has("errorMessage")){
                return new ResponseEntity<>(responseobj.toString(),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(responseobj.toString(),HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(isvalidlogin.toString(),HttpStatus.BAD_REQUEST);
        }
    }





}
