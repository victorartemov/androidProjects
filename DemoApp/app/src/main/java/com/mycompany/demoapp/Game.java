package com.mycompany.demoapp;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<GameItem> gameItems = new ArrayList<GameItem>();
    private Random rnd = new Random();

    public Game() {
    }

    public void setGameItems(ArrayList<GameItem> questions) {
        this.gameItems = questions;
    }

    public void addGameItem(GameItem question) {
        this.gameItems.add(question);
    }

    public GameItem getRandomItem() {
        return gameItems.get(rnd.nextInt(gameItems.size()));
    }
}
