package com.amujournal.amuJournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AmuJournalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmuJournalAppApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager manager(MongoDatabaseFactory dbFactry){ return new MongoTransactionManager(dbFactry);
    }
//
}