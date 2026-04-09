package org.example;

import org.example.model.*;
import org.example.service.GameService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // 🔴 ROBAR CARTA
    @MessageMapping("/draw")
    @SendTo("/topic/game")
    public GameState draw(@Payload String playerId) {
        gameService.drawCard(playerId);
        return gameService.getGame();
    }

    @MessageMapping("/play")
    @SendTo("/topic/game")
    public GameState play(@Payload PlayRequest req) {
        gameService.playCard(req.playerId, req.cardId);
        return gameService.getGame();
    }

    @MessageMapping("/move")
    @SendTo("/topic/game")
    public GameState move(@Payload MoveRequest req) {
        gameService.moveCard(req.playerId, req.cardId, req.x, req.y);
        return gameService.getGame();
    }
}