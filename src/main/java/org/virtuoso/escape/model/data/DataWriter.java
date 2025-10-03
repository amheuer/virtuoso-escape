package org.virtuoso.escape.model.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.stream.Stream;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.virtuoso.escape.model.GameState;
import org.virtuoso.escape.model.account.Account;

public class DataWriter {
    private static final String GAME_STATES_PATH = "json/gamestates.json";
    private static final String ACCOUNTS_PATH = "json/accounts.json";


    public static void writeGameState() {
        GameState currentGameState = GameState.getInstance();
        JSONObject currentGameStateMap = loadGameStateInfo(currentGameState);
        JSONObject allGameStatesMap = DataLoader.loadAllStates();
        allGameStatesMap.put(currentGameState.getAccount().getId().toString(), currentGameStateMap);
        writeToFile(ACCOUNTS_PATH, allGameStatesMap);
    }

    public static void writeAccount() {
        Account currentAccount = GameState.getInstance().getAccount();
        JSONObject currentAccountMap = loadAccountInfo(currentAccount);
        JSONObject allAccountsMap = DataLoader.loadAllAccounts();
        allAccountsMap.put(currentAccount.getId().toString(), currentAccountMap);
        writeToFile(GAME_STATES_PATH, allAccountsMap);
    }
    private static void writeToFile(String filepath, JSONObject json){
            try (FileWriter file = new FileWriter(filepath)) {
 
            file.write(json.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject loadGameStateInfo(GameState gameState){
        JSONObject gameStateMap = new JSONObject();
        gameStateMap.put("currentFloor", gameState.getCurrentFloor().getId());
        gameStateMap.put("currentRoom", gameState.getCurrentRoom().getId());
        gameStateMap.put("currentEntity", gameState.getCurrentEntity().getId());
        gameStateMap.put("currentItems",new JSONArray(getItemIds(gameState.getCurrentItems())));
        gameStateMap.put("time", gameState.getTime());
        gameStateMap.put("difficulty", gameState.getDifficulty().toString());
        return gameStateMap;
    }

    private static String[] getItemIds(ArrayList<Item> currentItems) {
        return Arrays.stream(currentItems).map(item -> item.getId()).toArray();
    }

    private static JSONObject loadAccountInfo(Account account){
        JSONObject accountMap = new JSONObject();
        accountMap.put("username", account.getUsername());
        accountMap.put("hashedPassword", account.getHashedPassword());
        accountMap.put("highScore", account.getHighScore());
        return accountMap;
    }
}

