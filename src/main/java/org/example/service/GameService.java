package org.example.service;

import org.example.model.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameState game = new GameState();

    public GameState getGame() {
        return game;
    }

    public Card drawCard(String playerId) {
        if (game.deck.isEmpty()) return null;

        Card card = game.deck.remove(0);
        game.players.get(playerId).hand.add(card);

        return card;
    }

    public void playCard(String playerId, String cardId) {
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