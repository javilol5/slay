package org.example;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/play")
    @SendTo("/topic/game")
    public String play(String move) {
        return "Movimiento recibido: " + move;
    }
}