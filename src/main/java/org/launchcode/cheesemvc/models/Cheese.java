package org.launchcode.cheesemvc.models;

public class Cheese {

    private int id;
    private static int nextId=1;
    private String name;
    private String description;

    // Constructors
    public Cheese() {
        id=nextId;
        nextId++;
    }

   // public Cheese(String name) {
   //     this();
   //     this.name=name;
   // }

    public Cheese(String name, String description) {
        this();
        this.name=name;
        this.description=description;
    }

    //getters and setters
    public int getId() {
        return id;
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
}
