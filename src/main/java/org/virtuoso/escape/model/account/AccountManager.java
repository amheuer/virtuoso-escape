package org.virtuoso.escape.model.account;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author gabri
 */
public class AccountManager {
 // TODO(gabri) add logic
    private static AccountManager accountManager;
    private HashMap<String, Account> accounts;

    AccountManager() {

    }
    public static AccountManager getInstance(){
        return null;
    }

    public Optional<Account> login(String username, String password) {
        return null;
    }
    public Optional<Account> newAccount(String username, String password){
        return null;
    }
    public void logout() {
        return;
    }

}