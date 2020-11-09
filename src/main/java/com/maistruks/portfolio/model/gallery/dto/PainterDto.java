package com.maistruks.portfolio.model.gallery.dto;

public class PainterDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String painterInfo;

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

    public String getPainterInfo() {
        return painterInfo;
    }

    public void setPainterInfo(String painterInfo) {
        this.painterInfo = painterInfo;
    }
}
