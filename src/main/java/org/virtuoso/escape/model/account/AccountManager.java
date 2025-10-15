package org.virtuoso.escape.model.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.virtuoso.escape.model.GameState;
import org.virtuoso.escape.model.data.*;

/**
 * @author Treasure
 * @author gabri
 * @author Andrew
 */
public class AccountManager {
	private static AccountManager accountManager;
	public JSONObject accounts;
	public JSONObject gameStates;

	// private AccountManager() {
	// 	this.accounts = new HashMap<>();
	// 	JSONObject accts = DataLoader.loadAccounts();
	// 	for (Object id : accts.keySet()) {
	// 		Object value = accts.get(id);
	// 		if (value instanceof JSONObject acct) {
	// 			String username = acct.get("username").toString();
	// 			String password = acct.get("hashedPassword").toString();
	// 			this.accounts.put(username, new Account(username, password));
	// 		}
	// 	}
	// 	this.gameStates = DataLoader.loadGameStates();
	// }

	public static AccountManager instance() {
		if (accountManager == null) {
			accountManager = new AccountManager();
		}
		return accountManager;
	}

	// public Optional<Account> login(String username, String password) {
	// 	GameState gameState = GameState.instance();
	// 	Account account = this.accounts.get(username);
	// 	String hashedPassword = Account.hashPassword(password);
	// 	if (account != null && account.hashedPassword().equals(hashedPassword)) {
	// 		gameState.begin(account);
	// 		return Optional.of(account);
	// 	}
	// 	return Optional.empty();
	// }

	public Optional<Account> newAccount(String username, String password) {
		GameState gameState = GameState.instance();
		Account account = new Account(username, password);
		gameState.begin(account);
		return Optional.of(account);
	}

	public void logout() {
		DataWriter.writeAccount();
	}
}