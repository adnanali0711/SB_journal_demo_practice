package com.amujournal.amuJournal.controller;

import com.amujournal.amuJournal.entity.JournalEntry;
import com.amujournal.amuJournal.entity.User;
import com.amujournal.amuJournal.service.userService;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amujournal.amuJournal.service.journalEntryService;


import java.util.*;

@RestController
@RequestMapping("/journal")


public class jornalEntryControl {

    @Autowired
    private journalEntryService journalEntryService;
    @Autowired
    private userService userService;


    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournals(@PathVariable String userName){
        User entryByUserName = userService.getEntryByUserName(userName);
        List<JournalEntry> entries = entryByUserName.getJournalEntries();
        if(entries!=null && !entries.isEmpty()){
            return new ResponseEntity<>(entries,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try{
            User user = userService.getEntryByUserName(userName);
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("id/{myID}")
    public ResponseEntity<JournalEntry> getEntryByID(@PathVariable ObjectId myID){
       Optional<JournalEntry> journalEntry =  journalEntryService.getEntryById(myID);
       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }
    @DeleteMapping("id/{userName}/{myID}")
    public ResponseEntity<?> deleteEntryByID(@PathVariable ObjectId myID, @PathVariable String userName) {
        journalEntryService.deleteById(myID,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myID}")
    public JournalEntry updateEntry(@PathVariable ObjectId myID, @RequestBody JournalEntry newEntry,@PathVariable String userName){
        JournalEntry oldEntry = journalEntryService.getEntryById(myID).orElse(null);
        if(oldEntry!=null){
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());

//            journalEntryService.saveEntry(oldEntry, userName);
        }
//        System.out.println();
            return oldEntry;
    }

}
