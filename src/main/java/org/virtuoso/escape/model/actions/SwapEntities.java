package org.virtuoso.escape.model.actions;

import org.virtuoso.escape.model.Entity;
import org.virtuoso.escape.model.GameState;

import java.util.ArrayList;

public record SwapEntities(Entity toPlace, Entity toReplace) implements Action {
    @Override
    public void execute() {
        // TODO(gabri) figure out how do this
        ArrayList<Room> rooms = GameState.getInstance().currentFloor.getRooms();
        ArrayList<String> j;
        // TODO(gabri) don't throw if the room doesn't exist.
        for (var room: rooms){
            ArrayList<Entity> ents = room.getEntities();
            if (ents.stream().noneMatch((ent) -> ent.getId() == toPlace.getId())) continue;
            ents.set(ents.indexOf(toReplace), toPlace); // TODO(gabri) test this and see if toReplace has somehow been copied.
        }
    }
}
