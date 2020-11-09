package com.maistruks.portfolio.service.battleShip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.maistruks.portfolio.model.battleShip.Field;

public class ComputerAI {


    private List<Field> enemyShots = new ArrayList<>();
    private Random random = new Random();
    private List<Integer> shotPlaces = new ArrayList<>();

    public ComputerAI() {
        enemyShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "enemyShot_" + getLatter(i) + j;
                Field field = new Field(parameter, false);
                enemyShots.add(field);
                System.out.println(field);
            }
        }
    }

    public List<Field> getEnemyShots() {
        return enemyShots;
    }

    public void setEnemyShots(List<Field> enemyShots) {
        this.enemyShots = enemyShots;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public List<Integer> getShotPlaces() {
        return shotPlaces;
    }

    public void setShotPlaces(List<Integer> shotPlaces) {
        this.shotPlaces = shotPlaces;
    }

    public void enemyHit() {
        boolean check = true;
        int hitIndex = 0;
        while (check) {
            check = false;
            hitIndex = random.nextInt(100);
            for (Integer num : shotPlaces) {
                if (num.equals(hitIndex)) {
                    check = true;
                }
            }
        }
        enemyShots.get(hitIndex).setValue(true);
        shotPlaces.add(hitIndex);
    }

    public String getLatter(int i) {
        if (i == 1) {
            return "a";
        } else if (i == 2) {
            return "b";
        } else if (i == 3) {
            return "c";
        } else if (i == 4) {
            return "d";
        } else if (i == 5) {
            return "e";
        } else if (i == 6) {
            return "f";
        } else if (i == 7) {
            return "g";
        } else if (i == 8) {
            return "h";
        } else if (i == 9) {
            return "i";
        } else if (i == 10) {
            return "j";
        } else {
            return "";
        }
    }
}
