package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    @Autowired
    private JavaMailSender mailSender;
    @GetMapping("")
    public String showForm(){
        return "/login";
    }
    @PostMapping("/login")
    public String sendEmail(HttpServletRequest request, @RequestParam String username,
                          @RequestParam String password, @RequestParam String email) {
        // use mailSender here...
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thien97.night1@gmail.com");
        message.setTo("long1110dn@gmail.com");
        String mailSubject = username + " has send a message";
        String mailContent = "Sender Name : " + username + "\n";
        mailContent += "Sender Email: " + email;
        message.setSubject(mailSubject);
        message.setText(mailContent);
        mailSender.send(message);

        return "/message";
    }
}
