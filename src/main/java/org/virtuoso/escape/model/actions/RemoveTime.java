package org.virtuoso.escape.model.actions;

import org.virtuoso.escape.model.GameState;

import java.time.Duration;

public record RemoveTime(Duration time) implements Action{
    @Override
    public void execute() {
        GameState.getInstance().addTime(time.negated());
    }
}
