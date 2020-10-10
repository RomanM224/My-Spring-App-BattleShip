package com.maistruk.springapp.model.battleShip;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    private List<Ship> ships;
    public  List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public String toString() {
        return "Fleet [ships=" + ships + "]";
    }

    public List<Field> getAllFields() {
        List<Field> allFields = new ArrayList<>();
        for (Ship ship : ships) {
            for (Field field : ship.getShipFields()) {
                allFields.add(field);
            }
        }
        return allFields;
    }

    public String ifExistField(String fieldName) {
        List<Field> fields = getAllFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return "checked";
            }
        }
        return "";
    }
    
    public void destroyEnemyShip(List<Field> fileds) {
        Field destroyField = null;
        int shipNum = 0;
        int i = 0;
        for(Ship ship : ships) {
            for(Field shipField : ship.getShipFields()) {
                for(Field fild : fileds) {
                    String str1 = shipField.getName().substring(6, 8);
                    String str2 = fild.getName().substring(7, 9);
                    if(str1.equals(str2) && shipField.getValue() == true && fild.getValue() == true) {
                        destroyField = shipField;
                        shipNum = i;
                    }
                }
                
            }
            i++;
        }
        Ship sh = ships.get(shipNum);
        sh.getShipFields().remove(destroyField);
        if(sh.getShipFields().size() == 0) {
            ships.remove(sh);
        }
    }
    
    public void destroyMyShip(List<Field> fileds) {
        Field destroyField = null;
        int shipNum = 0;
        int i = 0;
        for(Ship ship : ships) {
            for(Field shipField : ship.getShipFields()) {
                for(Field fild : fileds) {
                    String myShip = shipField.getName().substring(3, 5);
                    String enemyHit = fild.getName().substring(10, 12);
                    if(myShip.equals(enemyHit) && shipField.getValue() == true && fild.getValue() == true) {
                        destroyField = shipField;
                        shipNum = i;
                    }
                }
                
            }
            i++;
        }
        Ship sh = ships.get(shipNum);
        sh.getShipFields().remove(destroyField);
        if(sh.getShipFields().size() == 0) {
            ships.remove(sh);
        }
    }
    
    public int getShipFieldsBySize(int shipSize) {
        for(Ship ship : ships) {
            if(ship.getShipSize() == shipSize) {
                return ship.getShipFields().size();
            }
        }
        
        
        return 0;
    }

}
