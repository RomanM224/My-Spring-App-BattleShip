package com.maistruks.portfolio.model.gallery;

import java.util.List;

public class Painter { 

    private Integer id;
    private String firstName;
    private String lastName;
    private String info;
    private List<Painting> paintings;
    
    public Painter() {
    }

    public Painter(Integer id, String firstName, String lastName, String info) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }
    
    
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
