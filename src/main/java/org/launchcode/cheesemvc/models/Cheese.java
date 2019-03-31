package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    private int cheeseId;
    private static int nextId=0;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    private  CheeseType type;

    @NotNull
    @Size(min=1, message="Description Field Must Not Be Empty.")
    private String description;

    @NotNull
    @Range(min = 1, max = 5)
    private int rating;

    // Constructors
    public Cheese() {
        cheeseId=nextId;
        nextId=nextId + 1;
    }

    public Cheese(String name, String description) {
        this();
        this.name=name;
        this.description=description;
    }

    //getters and setters
    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId=cheeseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name=aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description=aDescription;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
