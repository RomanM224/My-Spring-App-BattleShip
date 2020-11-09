package com.maistruks.portfolio.model.battleShip;

public class Field {
    
    private String name;
    private Boolean value;
    
    public Field() {}
    
    public Field(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getValue() {
        return value;
    }
    public void setValue(Boolean value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Field [name=" + name + ", value=" + value + "]";
    }
    
    public String ifExistField() {
        if(value) {
            return "checked";
        }
        return "";
    }
    
    public String ifExistField(String name) {
        if(value && name.equals(this.name)) {
            return "checked";
        }
        return "";
    }
    
    
}
