package org.example.service;

import org.example.model.*;
import org.example.model.Card;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private GameState game = new GameState();

    public GameService() {
        // Crear mazo de prueba
        for (int i = 0; i < 20; i++) {
            Card c = new Card();
            c.id = "card-" + i;
            c.name = "Carta " + i;
            c.image = "/img/carta" + (i % 5) + ".png";
            game.deck.add(c);
        }
    }

    public GameState getGame() {
        return game;
    }

    public void ensurePlayer(String playerId) {
        game.players.putIfAbsent(playerId, new Player());
    }

    public void drawCard(String playerId) {
        ensurePlayer(playerId);

        if (game.deck.isEmpty()) return;

        Card card = game.deck.remove(0);
        game.players.get(playerId).hand.add(card);
    }

    public void playCard(String playerId, String cardId) {
        ensurePlayer(playerId);

        Player player = game.players.get(playerId);

        Card selected = null;

        for (Card c : player.hand) {
            if (c.id.equals(cardId)) {
                selected = c;
                break;
            }
        }

        if (selected != null) {
            player.hand.remove(selected);

            // posición inicial
            selected.x = 100;
            selected.y = 100;

            player.board.add(selected);
        }
    }

    public void moveCard(String playerId, String cardId, int x, int y) {
        Player player = game.players.get(playerId);

        for (Card c : player.board) {
            if (c.id.equals(cardId)) {
                c.x = x;
                c.y = y;
                return;
            }
        }
    }


}