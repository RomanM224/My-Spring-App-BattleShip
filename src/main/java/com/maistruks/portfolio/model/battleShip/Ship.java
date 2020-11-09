package com.maistruks.portfolio.model.battleShip;

import java.util.List;

public class Ship {
    
    private List<Field> shipFields;
    private int shipSize;

    public int getShipSize() {
        return shipSize;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public List<Field> getShipFields() {
        return shipFields;
    }

    public void setShipFields(List<Field> shipFields) {
        this.shipFields = shipFields;
    }

    @Override
    public String toString() {
        return "Ship [shipFields=" + shipFields + "]";
    }

    
    

}
