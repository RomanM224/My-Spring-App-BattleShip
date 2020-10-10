package com.maistruk.springapp.service.battleShip;

public class TableCreater {

    public String createFrame(int i, int j) {
        if (i == 0 && j == 0) {
            return "";
        } else if (i == 0 && j == 1) {
            return "1";
        } else if (i == 0 && j == 2) {
            return "2";
        } else if (i == 0 && j == 3) {
            return "3";
        } else if (i == 0 && j == 4) {
            return "4";
        } else if (i == 0 && j == 5) {
            return "5";
        } else if (i == 0 && j == 6) {
            return "6";
        } else if (i == 0 && j == 7) {
            return "7";
        } else if (i == 0 && j == 8) {
            return "8";
        } else if (i == 0 && j == 9) {
            return "9";
        } else if (i == 0 && j == 10) {
            return "10";
        } else if (i == 1 && j == 0) {
            return "A";
        } else if (i == 2 && j == 0) {
            return "B";
        } else if (i == 3 && j == 0) {
            return "C";
        } else if (i == 4 && j == 0) {
            return "D";
        } else if (i == 5 && j == 0) {
            return "E";
        } else if (i == 6 && j == 0) {
            return "F";
        } else if (i == 7 && j == 0) {
            return "G";
        } else if (i == 8 && j == 0) {
            return "H";
        } else if (i == 9 && j == 0) {
            return "I";
        } else if (i == 10 && j == 0) {
            return "J";
        }
        return "";
    }

    public String getMyTablePart(int i, int j, String[] isChecked) {
        String fieldName = getLatter(i) + j;
        String name = "enemyShot_" + fieldName;
        return "<label class=\"" + isChecked[0] + "\" ><input type=\"checkbox\" name=\"" + name + "\" class=\"unclicable\"" + isChecked[1]
                + ">" + "<span class=\"" + isChecked[2] + "\"></span> </label>";
    }

    public String getEnemyTablePart(int i, int j, String[] isChecked) {
        String fieldName = getLatter(i) + j;
        String name = "myShot_" + fieldName;
        return "<label class=\"" + isChecked[0] + "\" ><input type=\"checkbox\" name=\"" + name + "\" class=\"" + isChecked[3] + "\" " + isChecked[1]
                + ">" + "<span class=\"" + isChecked[2] + "\"></span> </label>";
    }
    
    public String showEnemyShipsTablePart(int i, int j, String[] isChecked) {
        String fieldName = getLatter(i) + j;
        String name = "" + fieldName;
        return "<label class=\"" + isChecked[0] + "\" ><input type=\"checkbox\" name=\"" + name + "\"" + isChecked[1]
                + ">" + "<span class=\"" + isChecked[2] + "\"></span> </label>";
    }

    public String getFirstTablePart(int i, int j, String[] isChecked) {
        String fieldName = getLatter(i) + j;
        String name = "my_" + fieldName;
        return "<label class=\"" + isChecked[0] + "\" ><input type=\"checkbox\" name=\"" + name + "\"" + isChecked[1]
                + ">" + "<span class=\"" + isChecked[2] + "\"></span> </label>";
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
