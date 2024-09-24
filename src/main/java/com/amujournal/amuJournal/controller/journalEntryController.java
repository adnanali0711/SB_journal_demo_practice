package com.amujournal.amuJournal.controller;

import com.amujournal.amuJournal.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/journal")

public class journalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAllJournals(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myID}")
    public JournalEntry getEntryByID(@PathVariable Long myID){
            System.out.println("Success");
            return journalEntries.get(myID);
    }
    @DeleteMapping("id/{myID}")
    public boolean deleteEntryByID(@PathVariable Long myID) {
        System.out.println("Success");
        journalEntries.remove(myID);
        return true;
    }

    @PutMapping("/id/{myID}")
    public JournalEntry updateEntry(@PathVariable Long myID,@RequestBody JournalEntry entry){
        return journalEntries.put(myID,entry);
    }
    
}
