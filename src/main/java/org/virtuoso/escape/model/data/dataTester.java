package org.virtuoso.escape.model.data;

import java.util.HashMap;

import org.virtuoso.escape.model.GameState;
import org.virtuoso.escape.model.account.Account;

public class dataTester {
    private DataLoader dl = new DataLoader();
    private HashMap<String, String> state = dl.loadAccounts();
}
