package com.ironhack.edgeservice.email;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}
