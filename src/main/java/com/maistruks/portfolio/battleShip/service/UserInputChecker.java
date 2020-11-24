package com.maistruks.portfolio.battleShip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruks.portfolio.battleShip.model.Field;
import com.maistruks.portfolio.battleShip.model.Fleet;
import com.maistruks.portfolio.battleShip.model.Ship;
import com.maistruks.portfolio.exception.BattleShipException;



@Service
public class UserInputChecker {
    
    @Autowired
    private GamaManager gamaManager;

    public void checkInput(List<Field> fields) throws BattleShipException {
        for (int i = 0; i < fields.size(); i++) {
            if ((i < 90) && (i % 10 != 9)) {
                if (fields.get(i).getValue() == true && fields.get(i + 11).getValue() == true) {
                    throw new BattleShipException("Your ships are located wrongly, probably too close to each other. <br>Should be one empty block between ships");
                } else if (fields.get(i + 10).getValue() == true && fields.get(i + 1).getValue() == true) {
                    throw new BattleShipException("Your ships are located wrongly, probably too close to each other. <br>Should be one empty block between ships");
                }
            }
        }
        Fleet fleet = gamaManager.generateFleet(fields);
        if(fleet.getShips().size() != 5) {
            throw new BattleShipException("You should have 5 ships");
        }
        List<Integer> shipsSize = new ArrayList<Integer>();
        for(Ship ship :fleet.getShips()) {
            shipsSize.add(ship.getShipFields().size());
        }
        int min = shipsSize.stream().mapToInt(v -> v).min().orElse(0);
        int max = shipsSize.stream().mapToInt(v -> v).max().orElse(0);
        shipsSize = shipsSize.stream().distinct().collect(Collectors.toList());
        if((min != 1) || (max != 5) || (shipsSize.size() != 5)) {
            throw new BattleShipException("Your ships are located wrongly <br> You should have: <br> 1 ship with 1 block <br> 1 ship with 2 block <br> 1 ship with 3 block <br> 1 ship with 4 block <br> 1 ship with 5 block");
        }
    }

}
