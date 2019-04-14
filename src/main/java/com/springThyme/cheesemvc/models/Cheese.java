package com.springThyme.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    private static int nextID = 0;
    private int ID;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    private String description;

    public Cheese() {
        this.ID = ++nextID;
    }

    public Cheese(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getName()  + ": " + this.getDescription();
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
