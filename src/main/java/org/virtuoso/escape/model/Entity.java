package org.virtuoso.escape.model;

import org.virtuoso.escape.model.actions.Action;
import org.virtuoso.escape.model.actions.TakeInput;

public class Entity {
    private String id;
    private Action attackAction;
    private Action inspectAction;
    private Action interactAction;
    private TakeInput inputAction;

    public Entity(String id, Action attackAction, Action inspectAction, Action interactAction) {
        this.id = id;
        this.attackAction = attackAction;
        this.inspectAction = inspectAction;
        this.interactAction = interactAction;
    }

    private String getText(String key) {
        try {
            return GameInfo.getInstance().getLanguage(this.id).get(key);
        } catch (Exception e) {
            return "[Missing text: " + key + "]";
        }
    }

    public void interact(Item item) {
        String message = null;
        if (item != null) {
            try {
                message = GameInfo.getInstance().getLanguage(this.id).get(item.id());
            } catch (Exception ignored) {}
        }
        if (message == null) message = getText("interact");

        GameState.instance().setCurrentMessage(message);

        if (interactAction != null) {
            interactAction.execute();
        }
    }

    public void attack() {
        GameState.instance().setCurrentMessage(getText("attack"));
        if (attackAction != null) attackAction.execute();
    }

    public void inspect() {
        GameState.instance().setCurrentMessage(getText("inspect"));
        if (inspectAction != null) inspectAction.execute();
    }

    public void introduce() {
        GameState.instance().setCurrentMessage(getText("introduce"));
    }

    public boolean equals(Entity other) {
        return this.id.equals(other.id);
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return GameInfo.getInstance().getLanguage(this.id).get("name");
    }

    public void takeInput(String input) {
    String message = GameInfo.getInstance().getLanguage(this.id).get(input);
    if (message != null) {
        GameState.instance().setCurrentMessage(message);
    }

    if (inputAction != null) {
        inputAction.withInput(input).execute();
    }
	}

}
