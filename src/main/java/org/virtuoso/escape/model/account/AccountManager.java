package org.virtuoso.escape.model.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Treasure
 * @author gabri
 */
public class AccountManager {
    private static AccountManager accountManager;
    private HashMap<String, Account> accounts;

    private AccountManager() {
		this.accounts = new HashMap<>();
	    HashMap<String, String> accts = DataLoader.loadAccounts();

		for (Map.Entry<String, String> account : accts.entrySet()){
			String username = account.getKey();
			String password = account.getValue();
			this.accounts.put(username, new Account(username, password));
		}
    }

    public static AccountManager getInstance(){
        if (accountManager == null){
			accountManager = new AccountManager();
        }
		return accountManager;
    }

    public Optional<Account> login(String username, String password) {
        Account account = this.accounts.get(username);
		String hashedPassword = Account.hashPassword(password);
		if (account != null && account.getHashedPassword().equals(hashedPassword)){
			return Optional.of(account);
		}
		return Optional.empty();
    }

    public Optional<Account> newAccount(String username, String password){
        return Optional.of(new Account(username, password));
    }

    public void logout() {
        DataWriter.saveAccounts();
    }
}