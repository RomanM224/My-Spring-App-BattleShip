package com.maistruks.portfolio.service.battleShip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.maistruks.portfolio.model.battleShip.Field;


@Service
public class ShipsGenerator {

    public List<Field> generateShips() {
        List<Field> fields = getBattleground();
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            if (random.nextBoolean()) {
                generateHorizontalShip(fields, random, i);
            } else {
                generateVerticalShip(fields, random, i);
            }
        }

        return fields;
    }

    public void generateHorizontalShip(List<Field> fields, Random random, int shipSize) {
        boolean check = true;
        while (check) {
            check = false;
            int fieldLetter = random.nextInt(10) + 1;
            int fieldNumber = random.nextInt(10) + 1;
            if (fieldNumber + shipSize > 10) {
                fieldNumber = fieldNumber - shipSize;
            }
            String fieldName = "enemy_" + getLatter(fieldLetter) + fieldNumber;
            int fieldIndex = getIndexOfElement(fields, fieldName);
            check = checkHorizontalShip(fields, fieldIndex, shipSize);
            if (check == false) {
                for (int i = fieldIndex; i <= fieldIndex + shipSize - 1; i++) {
                    fields.get(i).setValue(true);
                }
            }
        }
    }

    public void generateVerticalShip(List<Field> fields, Random random, int shipSize) {
        boolean check = true;
        while (check) {
            check = false;
            int fieldLetter = random.nextInt(10) + 1;
            int fieldNumber = random.nextInt(10) + 1;
            if (fieldLetter + shipSize > 10) {
                fieldLetter = fieldLetter - shipSize;
            }
            String fieldName = "enemy_" + getLatter(fieldLetter) + fieldNumber;
            int fieldIndex = getIndexOfElement(fields, fieldName);
            check = checkVerticalShip(fields, fieldIndex, shipSize);
            if (check == false) {
                for (int i = fieldIndex; i <= fieldIndex + (shipSize - 1) * 10; i += 10) {
                    fields.get(i).setValue(true);
                }
            }
        }
    }

    private boolean checkVerticalShip(List<Field> fields, int fieldIndex, int shipSize) {
        if (fieldIndex == 0) {
            return checkVerticalShipWhereIndex0(fields, fieldIndex, shipSize);
        } else if (fieldIndex == 9) {
            return checkVerticalShipWhereIndex9(fields, fieldIndex, shipSize);
        } else if (fieldIndex > 0 && fieldIndex < 9) {
            return checkVerticalShipWhereIndexMore0AndLess9(fields, fieldIndex, shipSize);
        } else if (fieldIndex % 10 == 0) {
            return checkVerticalShipWhereModulo10(fields, fieldIndex, shipSize);
        } else if (fieldIndex % 10 == 9) {
            return checkVerticalShipWhereModulo9(fields, fieldIndex, shipSize);
        } else {
            return checkVerticalShipWithOtherIndex(fields, fieldIndex, shipSize);
        }
    }

    private boolean checkVerticalShipWhereIndex0(List<Field> fields, int fieldIndex, int shipSize) {
        for (int i = 0; i <= shipSize * 10; i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 1; i <= 1 + (shipSize * 10); i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalShipWhereIndex9(List<Field> fields, int fieldIndex, int shipSize) {
        for (int i = 8; i <= 8 + (shipSize * 10); i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 9; i <= 9 + (shipSize * 10); i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalShipWhereIndexMore0AndLess9(List<Field> fields, int fieldIndex, int shipSize) {
        for (int i = fieldIndex; i <= fieldIndex + (shipSize * 10); i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex - 1; i <= (fieldIndex - 1) + (shipSize * 10); i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex + 1; i <= (fieldIndex + 1) + (shipSize * 10); i += 10) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalShipWhereModulo10(List<Field> fields, int fieldIndex, int shipSize) {
        if (fieldIndex + (shipSize * 10) == 90) {
            for (int i = fieldIndex - 10; i <= fieldIndex + (shipSize - 1) * 10; i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 1 - 10; i <= (fieldIndex + 1) + ((shipSize - 1) * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        } else {
            for (int i = fieldIndex - 10; i <= fieldIndex + shipSize * 10; i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 1 - 10; i <= (fieldIndex + 1) + (shipSize * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalShipWhereModulo9(List<Field> fields, int fieldIndex, int shipSize) {
        if (fieldIndex + (shipSize * 10) == 99) {
            for (int i = fieldIndex - 10; i <= fieldIndex + (shipSize - 1) * 10; i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 1 - 10; i <= (fieldIndex - 1) + ((shipSize - 1) * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        } else {
            for (int i = fieldIndex - 10; i <= fieldIndex + shipSize * 10; i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 1 - 10; i <= (fieldIndex - 1) + (shipSize * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalShipWithOtherIndex(List<Field> fields, int fieldIndex, int shipSize) {
        if (fieldIndex + (shipSize * 10) > 99) {
            for (int i = fieldIndex - 10; i <= fieldIndex + (shipSize - 1) * 10; i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 1 - 10; i <= (fieldIndex - 1) + ((shipSize - 1) * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 1 - 10; i <= (fieldIndex + 1) + ((shipSize - 1) * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        } else {
            for (int i = fieldIndex - 10; i <= fieldIndex + shipSize * 10; i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 1 - 10; i <= (fieldIndex - 1) + (shipSize * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 1 - 10; i <= (fieldIndex + 1) + (shipSize * 10); i += 10) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalShip(List<Field> fields, int fieldIndex, int shipSize) {
        if (fieldIndex == 0) {
            return checkHorizontalShipWhereIndex0(fields, fieldIndex, shipSize);
        } else if (fieldIndex == 90) {
            return checkHorizontalShipWhereIndex90(fields, fieldIndex, shipSize);
        } else if (fieldIndex % 10 == 0) {
            return checkHorizontalShipWhereModulo10(fields, fieldIndex, shipSize);
        } else if (fieldIndex < 10) {
            return checkHorizontalShipWhereIndexLess10(fields, fieldIndex, shipSize);
        } else if (fieldIndex > 90) {
            return checkHorizontalShipWhereIndexMore90(fields, fieldIndex, shipSize);
        } else {
            return checkHorizontalShipWithOtherIndex(fields, fieldIndex, shipSize);
        }
    }

    private boolean checkHorizontalShipWhereIndex0(List<Field> fields, int fieldIndex, int shipSize) {
        for (int i = 0; i <= shipSize; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 10; i <= shipSize + 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontalShipWhereIndex90(List<Field> fields, int fieldIndex, int shipSize) {
        for (int i = 80; i <= 80 + shipSize; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 90; i <= 90 + shipSize; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontalShipWhereModulo10(List<Field> fields, int fieldIndex, int shipSize) {
        for (int i = fieldIndex; i <= fieldIndex + shipSize; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex - 10; i <= fieldIndex + shipSize - 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex + 10; i <= fieldIndex + shipSize + 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontalShipWhereIndexLess10(List<Field> fields, int fieldIndex, int shipSize) {
        if ((fieldIndex + shipSize) % 10 == 0) {
            for (int i = fieldIndex - 1; i <= fieldIndex + shipSize - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 10 - 1; i <= fieldIndex + shipSize + 10 - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        } else {
            for (int i = fieldIndex - 1; i <= fieldIndex + shipSize; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 10 - 1; i <= fieldIndex + shipSize + 10; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalShipWhereIndexMore90(List<Field> fields, int fieldIndex, int shipSize) {
        if ((fieldIndex + shipSize) % 10 == 0) {
            for (int i = fieldIndex - 1; i <= fieldIndex + shipSize - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 10 - 1; i <= fieldIndex + shipSize - 10 - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        } else {
            for (int i = fieldIndex - 1; i <= fieldIndex + shipSize; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 10 - 1; i <= fieldIndex + shipSize - 10; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalShipWithOtherIndex(List<Field> fields, int fieldIndex, int shipSize) {
        if ((fieldIndex + shipSize) % 10 == 0) {
            for (int i = fieldIndex - 1; i <= fieldIndex + shipSize - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 10 - 1; i <= fieldIndex + shipSize - 10 - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 10 - 1; i <= fieldIndex + shipSize + 10 - 1; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        } else {
            for (int i = fieldIndex - 1; i <= fieldIndex + shipSize; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex - 10 - 1; i <= fieldIndex + shipSize - 10; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
            for (int i = fieldIndex + 10 - 1; i <= fieldIndex + shipSize + 10; i++) {
                if (fields.get(i).getValue() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Field> getBattleground() {
        List<Field> fields = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "enemy_" + getLatter(i) + j;
                Field field = new Field(parameter, false);
                fields.add(field);
            }
        }
        return fields;
    }

    public int getIndexOfElement(List<Field> fields, String fieldName) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getName().equals(fieldName)) {
                return i;
            }
        }
        return 0;
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
