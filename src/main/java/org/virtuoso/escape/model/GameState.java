package org.virtuoso.escape.model;

import java.time.Duration;
import java.util.ArrayList;

import org.virtuoso.escape.model.account.Account;

public class GameState {
    private static GameState instance;
    private Floor currentFloor;
    private Entity currentEntity;
    private ArrayList<Item> currentItems;
    private Duration time;
    private Account account;

    private Difficulty difficulty;
    private String currentMessage;

    public static GameState getInstance() {
        if (instance == null) 
            instance = new GameState();
        return instance;
    }

    private GameState() {};

    public void begin(Account account){
        
    }

    public boolean hasItem(Item item){
        return currentItems.contains(item);
    }

        public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Entity getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
    }

    public ArrayList<Item> getCurrentItems() {
        return currentItems;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    p

    public Account getAccount() {
        return account;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public void write() {

    }
}
