package com.amujournal.amuJournal.journalEntryRepository;

import com.amujournal.amuJournal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface journalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {


}
