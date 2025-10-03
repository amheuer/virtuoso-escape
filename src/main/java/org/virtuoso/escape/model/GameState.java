package org.virtuoso.escape.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.virtuoso.escape.model.account.Account;
import org.virtuoso.escape.model.data.DataWriter;

public class GameState {
    private static GameState instance;
    private Floor currentFloor;
    private Room currentRoom;
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
        JSONObject gameStateInfo = DataLoader.loadGameStateInfo(account);
        this.currentFloor = GameInfo.getInstance().building.get((int) gameStateInfo.get("currentFloor"));
        this.currentRoom = currentFloor.getRooms().get((int) gameStateInfo.get("currentRoom"));
        this.currentEntity = currentRoom.getEntities().get((int) gameStateInfo.get("currentEntity"));
        this.currentItems = new ArrayList<Item>(Arrays.steam(gameStateInfo.getcurrentItems).map(itemString -> Item.valueOf(itemString)).toArray());
        this.time = Duration.ofSeconds((int) gameStateInfo.get("time"));
        this.account = account;
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void write() {
        DataWriter.writeGameState();
    }
}
