package com.amujournal.amuJournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/healthcheck")
    public String healthcheck(){ return "ok";}
<<<<<<< HEAD

=======
//    hey
>>>>>>> 7534dac (secondPushCheck)
}
