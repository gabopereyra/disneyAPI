package com.api.disney.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    private static final String SUBJECT = "Disney API by Gabo";
    private static final String TEXT = "Bienvenid@ a servicio API para jugar con el mundo de Disney!\nWith Love by Gabo.";

    public void sendThread(String to) {
        new Thread(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(SUBJECT);
            message.setText(TEXT);
            sender.send(message);
        }).start();
    }
}
