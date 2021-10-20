package fr.umontpellier.iut.exercice3;

public class NumeralToRoman {

    public String getRoman(int number){

        int unite = number %10;
        int dizaine = (number / 10) %10;
        int centaine = (number / 100) %10;
        int millier = (number / 1000) %10;

        if (0 <= number && number <= 3999)
            return getMillier(millier) + getCentaine(centaine) + getDizaine(dizaine) + getUnite(unite);
        else
            return "Pas dans l'intervalle. ";
    }

    public String getUnite (int n) {
        if (n == 0)
            return "";
        else if (n == 1)
            return "I";
        else if (n == 2)
            return "II";
        else if (n == 3)
            return "III";
        else if (n == 4)
            return "IV";
        else if (n == 5)
            return "V";
        else if (n == 6)
            return "VI";
        else if (n == 7)
            return "VII";
        else if (n == 8)
            return "VIII";
        else // if (n == 9)
            return "IX";
    }

    public String getDizaine (int n) {
        if (n == 0)
            return "";
        else if (n == 1)
            return "X";
        else if (n == 2)
            return "XX";
        else if (n == 3)
            return "XXX";
        else if (n == 4)
            return "XL";
        else if (n == 5)
            return "L";
        else if (n == 6)
            return "LX";
        else if (n == 7)
            return "LXX";
        else if (n == 8)
            return "LXXX";
        else //if (n == 9)
            return "XC";
    }

    public String getCentaine (int n) {
        if (n == 0)
            return "";
        else if (n == 1)
            return "C";
        else if (n == 2)
            return "CC";
        else if (n == 3)
            return "CCC";
        else if (n == 4)
            return "CD";
        else if (n == 5)
            return "D";
        else if (n == 6)
            return "DC";
        else if (n == 7)
            return "DCC";
        else if (n == 8)
            return "DCCC";
        else //if (n == 9)
            return "CM";
    }

    public String getMillier (int n) {
        if (n == 0)
            return "";
        else if (n == 1)
            return "M";
        else if (n == 2)
            return  "MM";
        else //if (n == 3);
        return "MMM";
    }
}