package org.virtuoso.escape.model;

import org.virtuoso.escape.model.account.*;

import java.util.ArrayList;
import java.util.Optional;

public class GameProjection {
	private GameState gameState;
	private AccountManager accountManager;

	public GameProjection() {
		this.accountManager = AccountManager.getInstance();
		this.gameState = GameState.getInstance();
	}

	public boolean login(String username, String password) {
		Optional<Account> currentAccount = this.accountManager.login(username, password);
		return currentAccount.isPresent();
	}

	public boolean createAccount(String username, String password) {
		Optional<Account> currentAccount = this.accountManager.newAccount(username, password);
		return currentAccount.isPresent();
	}

	public void logout() {
		this.accountManager.logout();
	}

	public Room getCurrentRoom() {
		return this.gameState.getCurrentRoom();
	}

	public ArrayList<Entity> getRoomEntities() {
		return getCurrentRoom().getEntities();
	}

	public Entity getCurrentEntity() {
		return this.gameState.getCurrentEntity();
	}

	public Floor getCurrentFloor() {
		return this.gameState.getCurrentFloor();
	}

	public String getCurrentMessage() {
		return this.gameState.getCurrentMessage();
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

	public void interact() {
		getCurrentEntity().interact();
	}

	public void attack() {
		getCurrentEntity().attack();
	}

	public void inspect() {
		getCurrentEntity().inspect();
	}

	public void input(String input) {

	}
}
