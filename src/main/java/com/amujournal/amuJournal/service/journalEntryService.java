package com.amujournal.amuJournal.service;

import com.amujournal.amuJournal.entity.JournalEntry;
import com.amujournal.amuJournal.entity.User;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import com.amujournal.amuJournal.journalEntryRepository.journalEntryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {
    @Autowired
    private journalEntryRepository journalEntryRepository;

    @Autowired
    private userService userService;

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.getEntryByUserName(userName);
            System.out.println(user);
            System.out.println("saveEntry Start");
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            System.out.println(saved);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName){
        User user = userService.getEntryByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
        return true;
    }

}


// controller --> service --> repository