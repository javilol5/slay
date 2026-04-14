package org.example;

import org.example.model.*;
import org.example.service.GameService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class GameController {

    private final GameService gameService;

    private final SimpMessagingTemplate messagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    // 🔴 ROBAR CARTA
    @MessageMapping("/draw")
    @SendTo("/topic/game")
    public GameState draw(@Payload String playerId) {

        gameService.drawCard(playerId);
        System.out.println("DRAW recibido de: " + playerId);
        // enviar mano SOLO al jugador
        messagingTemplate.convertAndSendToUser(
                playerId,
                "/queue/hand",
                gameService.getGame().players.get(playerId).hand
        );

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