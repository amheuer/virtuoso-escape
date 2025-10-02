package org.virtuoso.escape.model.actions;

import java.util.Arrays;

public record Chain(Action... actions) implements Action{
    @Override
    public void execute() {
        Arrays.stream(actions).forEach(Action::execute);
    }
}
