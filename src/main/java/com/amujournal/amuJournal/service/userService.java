package com.amujournal.amuJournal.service;

import com.amujournal.amuJournal.entity.JournalEntry;
import com.amujournal.amuJournal.entity.User;
import com.amujournal.amuJournal.journalEntryRepository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class userService {
    @Autowired
    private userRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public User getEntryByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public User findUser(String user){
        return userRepository.findByUserName(user);
    }

}


// controller --> service --> repository