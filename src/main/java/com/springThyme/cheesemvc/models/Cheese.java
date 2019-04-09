package com.springThyme.cheesemvc.models;

import java.util.Objects;

public class Cheese {
    String name;
    String description;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() {

    }

    @Override
    public String toString() {
        return this.getName()  + ": " + this.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cheese cheese = (Cheese) o;
        return name.equals(cheese.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public String getName() {
        return name;
    }

    private String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
