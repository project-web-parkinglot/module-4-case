package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail() {
        // use mailSender here...
    }
}
