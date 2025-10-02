package org.virtuoso.escape.model.actions;

import org.virtuoso.escape.model.GameState;

public record GiveItem(Item item) implements Action{
    @Override
    public void execute() {
        GameState.getInstance().addItem(item);
    }
}
