package com.springThyme.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cheeses")
public class Cheese {

    @Id  //signifies that this field is the primary key
    @GeneratedValue  //auto generated by the database
    private int id;

    @NotNull
    @Size(min = 3, max = 15, message = "Cheese name must be between 3 and 15 characters")
    private String name;

    @NotNull
    @Size(min= 1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Category category;

    @NotNull
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private int rating;

//only no arg default constructor needed for hibernate and it is automatically created by Java
//    public Cheese() {
//    }

    @Override
    public String toString() {
        return this.getName()  + ": " + this.getDescription();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
