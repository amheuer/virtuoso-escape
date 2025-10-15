package org.virtuoso.escape.model.data;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.virtuoso.escape.model.GameState;
import org.virtuoso.escape.model.account.Account;
import org.virtuoso.escape.model.account.AccountManager;

public class dataTester {
    private static DataLoader dl;
    private static JSONObject states;
    private static AccountManager am;

    public static void main(String args[]){
		GameState gs = GameState.instance();
		gs.setCurrentMessage("Hello");
		DataWriter.writeGameState();

    }
    

}
