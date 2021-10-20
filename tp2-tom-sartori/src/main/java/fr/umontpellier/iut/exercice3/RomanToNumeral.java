package fr.umontpellier.iut.exercice3;

public class RomanToNumeral {

    public int getNumeral(String romanNumber) {

        int x = 0, som = 0, mem;

        for (int i = 0; i < romanNumber.length(); i++) {
            mem = x;
            x = getNum(romanNumber.charAt(i));

            if (mem < x) {
                som = som - 2 * mem + x;
            }
            else {
                som = som + x;
            }
        }
        return som;
    }

    public int getNum (char rom) {
        if (rom == 'I')
            return 1;
        else if (rom == 'V')
            return 5;
        else if (rom == 'X')
            return 10;
        else if (rom == 'L')
            return 50;
        else if (rom == 'C')
            return 100;
        else if (rom == 'D')
            return 500;
        else if (rom == 'M')
            return 1000;
        return 0;
    }
}