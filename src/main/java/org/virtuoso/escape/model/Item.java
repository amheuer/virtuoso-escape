package org.virtuoso.escape.model;

public enum Item {
    //TODO: add items
    ;

    private String name;
    private String id;

    Item(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}