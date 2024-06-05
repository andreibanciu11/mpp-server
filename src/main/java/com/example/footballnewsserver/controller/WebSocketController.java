package com.example.footballnewsserver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/teams")
    public String greeting(String message) {
        return "Hello, " + message + "!";
    }
}

