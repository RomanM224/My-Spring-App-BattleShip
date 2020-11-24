package com.maistruks.portfolio.gallery.model.dto;

public class PainterDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String painterInfo;
    
    public PainterDto() {
    }

    public PainterDto(Integer id, String firstName, String lastName, String painterInfo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.painterInfo = painterInfo;
    }
    
    public PainterDto(String firstName, String lastName, String painterInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.painterInfo = painterInfo;
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

    public String getPainterInfo() {
        return painterInfo;
    }

    public void setPainterInfo(String painterInfo) {
        this.painterInfo = painterInfo;
    }

    @Override
    public String toString() {
        return "PainterDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", painterInfo="
                + painterInfo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((painterInfo == null) ? 0 : painterInfo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PainterDto other = (PainterDto) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (painterInfo == null) {
            if (other.painterInfo != null)
                return false;
        } else if (!painterInfo.equals(other.painterInfo))
            return false;
        return true;
    }
    
    
}
