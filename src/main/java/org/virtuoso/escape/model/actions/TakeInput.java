package org.virtuoso.escape.model.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class TakeInput implements Action {
    String input;
    private final HashMap<String, Action> cases;
    private final Optional<Action> default_;
    public TakeInput(String input, HashMap<String, Action> cases, Optional<Action> default_) {
        this.input = input;
        this.cases = cases;
        this.default_ = default_;
    }
    public TakeInput(String input, HashMap<String, Action> cases){
        this(input, cases, Optional.empty());
    }


    @Override
    public void execute() {
        for (Map.Entry<String, Action> tuple : cases.entrySet()) {
            if (input.strip().toLowerCase().equals(tuple.getKey())) {
                tuple.getValue().execute();
            }
        }
    }

    public Action withInput(String input) {
        this.input = input;
        return this;
    }
}
