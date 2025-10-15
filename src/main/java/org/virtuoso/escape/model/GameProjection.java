package org.virtuoso.escape.model;

import org.virtuoso.escape.model.account.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Treasure
 */
public class GameProjection {
	private GameState gameState;
	private AccountManager accountManager;

	public GameProjection() {
		this.accountManager = AccountManager.instance();
		this.gameState = GameState.instance();
	}

	// public boolean login(String username, String password) {
	// 	Optional<Account> currentAccount = this.accountManager.login(username, password);
	// 	return currentAccount.isPresent();
	// }

	public boolean createAccount(String username, String password) {
		Optional<Account> currentAccount = this.accountManager.newAccount(username, password);
		return currentAccount.isPresent();
	}

	public void logout() {
		this.accountManager.logout();
	}

	public Room currentRoom() {
		return this.gameState.currentRoom();
	}

	public ArrayList<Entity> roomEntities() {
		return currentRoom().entities();
	}

	public Optional<Entity> currentEntity() {
		return this.gameState.currentEntity();
	}

	public Floor currentFloor() {
		return this.gameState.currentFloor();
	}

	public String currentMessage() {
		return this.gameState.currentMessage();
	}

	public void setDifficulty(Difficulty difficulty) {
		this.gameState.setDifficulty(difficulty);
	}

	public void pickEntity(Entity entity) {
		this.gameState.pickEntity(entity);
	}

	public void leaveEntity() {
		this.gameState.leaveEntity();
	}

	public void interact(Item item) {
		currentEntity().orElseThrow().interact(item);
	}

	public void attack() {
		currentEntity().orElseThrow().attack();
	}

	public void inspect() {
		currentEntity().orElseThrow().inspect();
	}

	public void input(String input) {
		currentEntity().orElseThrow().takeInput(input);
	}
}
