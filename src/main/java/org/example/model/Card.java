package org.example.model;

public class Card {
    public String imagePath;
    public String name;

    public Card(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}