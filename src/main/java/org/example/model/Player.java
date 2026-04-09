package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String id;
    public List<Card> hand = new ArrayList<>();
    public List<Card> board = new ArrayList<>();
}