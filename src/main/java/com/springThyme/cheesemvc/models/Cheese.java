package com.springThyme.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    private static int nextID = 0;
    private int ID;

    @NotNull
    @Size(min = 3, max = 15, message = "Cheese name must be between 3 and 15 characters")
    private String name;

    @NotNull
    @Size(min= 1, message = "Description must not be empty")
    private String description;

    private CheeseType type;


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

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}
