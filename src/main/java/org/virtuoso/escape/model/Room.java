package org.virtuoso.escape.model;

import java.util.ArrayList;

public class Room{
    private ArrayList<Entity> entities; 
    private String introMessage;
    private String id;

    public Room(ArrayList<Entity> entities, String id, String introMessage){
        this.id = id;
        this.introMessage = introMessage;
        this.entities = new ArrayList<Entity>();
    }

    public String getIntroMessage() {
        return this.introMessage;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

}