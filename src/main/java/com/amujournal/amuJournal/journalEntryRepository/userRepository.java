package com.amujournal.amuJournal.journalEntryRepository;

import com.amujournal.amuJournal.entity.JournalEntry;
import com.amujournal.amuJournal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


@Component
public interface userRepository extends MongoRepository<User,ObjectId> {
    User findByUserName(String userName);

}
