package org.virtuoso.escape.model.actions;

import java.util.function.BooleanSupplier;

public record Conditional(BooleanSupplier condition, Action if_, Action else_) implements Action {
    // Overloaded second operator for
    public Conditional(BooleanSupplier condition, Action if_) {
        this(condition, if_, null);
    }

    @Override
    public void execute() {
        if (condition.getAsBoolean()) if_.execute();
        else if (else_ != null) else_.execute();

    }
}
