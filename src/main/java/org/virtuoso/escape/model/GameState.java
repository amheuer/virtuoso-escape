package org.virtuoso.escape.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.virtuoso.escape.model.account.Account;
import org.virtuoso.escape.model.data.*;

/**
 * @author Andrew
 */
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

	public static GameState instance() {
		if (instance == null)
			instance = new GameState();
		return instance;
	}

	private GameState() {
	}

	public void begin(Account account) {
		JSONObject gameStateInfo = DataLoader.loadGameState(account);
		this.currentFloor = GameInfo.instance().building.get((int) gameStateInfo.get("currentFloor"));
		this.currentRoom = currentFloor.rooms().get((int) gameStateInfo.get("currentRoom"));
		this.currentEntity = currentRoom.entities().get((int) gameStateInfo.get("currentEntity"));
		this.currentItems = new ArrayList<Item>();
		JSONArray items = (JSONArray) gameStateInfo.get("currentItems");
		for (int i = 0; i < items.size(); i++) {
			currentItems.add(Item.valueOf((String) items.get(i)));
		}
		this.time = Duration.ofSeconds((int) gameStateInfo.get("time"));
		this.account = account;
	}

	public void pickEntity(Entity entity) {

	}

	public void leaveEntity() {

	}

	public boolean hasItem(Item item) {
		return currentItems.contains(item);
	}

	public void addItem(Item item) {
		currentItems.add(item);
	}

	public Floor currentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Floor currentFloor) {
		this.currentFloor = currentFloor;
	}

	public Optional<Entity> currentEntity() {
		return Optional.ofNullable(currentEntity);
	}

	public void setCurrentEntity(Entity currentEntity) {
		this.currentEntity = currentEntity;
	}


	public ArrayList<Item> currentItems() {
		return currentItems;
	}

	public Duration time() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

	public void addTime(Duration time) {
		this.time.plus(time);
	}

	public Account account() {
		return account;
	}

	public Difficulty difficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty diff) {
		this.difficulty = diff;
	}

	public String currentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(String currentMessage) {
		this.currentMessage = currentMessage;
	}

	public Room currentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public void write() {
		DataWriter.writeGameState();
	}
}
