package com.amujournal.amuJournal.controller;

import com.amujournal.amuJournal.entity.User;
import com.amujournal.amuJournal.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")


public class userController {

    @Autowired
    private userService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAll();
        if(users!=null && !users.isEmpty()){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        if(user!=null){
            userService.saveEntry(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User oldUser = userService.getEntryByUserName(user.getUserName());
        if(user!=null){
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userService.saveEntry(oldUser);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
