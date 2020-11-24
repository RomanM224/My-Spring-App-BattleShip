package com.maistruks.portfolio.battleShip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maistruks.portfolio.battleShip.model.Field;


@Service
public class FieldChecker {

    public String[] ifExistFieldMyFleet(String fieldName, List<Field> enemyShots, List<Field> myShipsFields) {
        boolean matchesWithFields = matchesWithMyFields(fieldName, enemyShots);
        boolean matchesWithMyShips = matchesWithMyShips(fieldName, myShipsFields);
        if(matchesWithMyShips && matchesWithFields) {
            return new String[]{"blueMark", "checked", "redMark"};
        } else if(matchesWithMyShips ) {
            return new String[]{"blueMark", "checked", "oragneMark"};
        } else if(matchesWithFields) {
            return new String[]{"blueMark", "checked", "greyMark"};
        }else {
            return new String[]{"blueMark", "", "greyMark"};
        }
    }
    
    public boolean matchesWithMyFields(String fieldName, List<Field> enemyShots) {
        fieldName = "enemyShot_" + fieldName;
        for (Field field : enemyShots) {
            if (field.getName().equals(fieldName) && field.getValue() == true) {
                return true;
            }
        }
        return false;
    }
    
    public boolean matchesWithMyShips(String fieldName, List<Field> myShipsFields) {
        fieldName = "my_" + fieldName;
        for(Field field : myShipsFields) {
            if(field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
    
    
    public String[] ifExistFieldEnemyFleet(String fieldName , List<Field> myShots, List<Field> enemyShipsFields) {
        boolean matchesWithEnemyFields = matchesWithEnemyFields(fieldName, myShots);
        boolean matchesWithEnemyShips = matchesWithEnemyShips(fieldName, enemyShipsFields);
        if(matchesWithEnemyShips && matchesWithEnemyFields) {
            return new String[]{"blueMark", "checked", "redMark", "unclicable"};
        } else if(matchesWithEnemyFields) {
            return new String[]{"blueMark", "checked", "greyMark", "unclicable"};
        }else {
            return new String[]{"blueMark", "", "greyMark", "product-list"};
        }
    }
    
    
    
    public boolean matchesWithEnemyFields(String fieldName, List<Field> myShots) {
        fieldName = "myShot_" + fieldName;
        for (Field field : myShots) {
            if (field.getName().equals(fieldName) && field.getValue() == true) {
                return true;
            }
        }
        return false;
    }
    
    public boolean matchesWithEnemyShips(String fieldName, List<Field> enemyShipsFields) {
        fieldName = "enemy_" + fieldName;
        for(Field field : enemyShipsFields) {
            if(field.getName().equals(fieldName) && field.getValue() == true) {
                return true;
            }
        }
        return false;
    }
    
    public String[] showEnemyFleet(String fieldName , List<Field> myShots, List<Field> enemyShipsFields) {
        boolean matchesWithEnemyFields = matchesWithEnemyFields(fieldName, myShots);
        boolean matchesWithEnemyShips = matchesWithEnemyShips(fieldName, enemyShipsFields);
        if(matchesWithEnemyShips) {
            return new String[]{"blueMark", "checked", "redMark"};
        } else if(matchesWithEnemyFields) {
            return new String[]{"blueMark", "checked", "greyMark"};
        }else {
            return new String[]{"blueMark", "", "greyMark"};
        }
    }

}
