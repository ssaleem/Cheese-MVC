package com.springThyme.cheesemvc.models;

import utils.CheeseNameValidator;

import java.util.Objects;

public class Cheese {

    private static int nextID = 0;
    private int ID;
    private String name;
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
