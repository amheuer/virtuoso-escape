package org.virtuoso.escape.model;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInfo {
    private static GameInfo instance;
    private HashMap<String,HashMap<String,String>> language = new HashMap<String,HashMap<String,String>>();
    public ArrayList<Floor> building = new ArrayList<Floor>();

    public static GameInfo getInstance() {
        if (instance == null) 
            instance = new GameInfo();
        return instance;
    }

    private GameInfo() {};

    public String getString(String id, String stringId) {
        return language.get(id).get(stringId);
    }

}