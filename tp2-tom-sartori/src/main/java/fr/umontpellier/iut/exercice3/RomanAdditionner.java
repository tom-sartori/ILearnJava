package fr.umontpellier.iut.exercice3;

public class RomanAdditionner {

    private NumeralToRoman numeralToRoman;  //= new NumeralToRoman();
    private RomanToNumeral romanToNumeral; // = new RomanToNumeral();

    public String compute(String ope) {

        if (ope.length() == 0)
            return "0";

        NumeralToRoman numeralToRoman = new NumeralToRoman();
        RomanToNumeral romanToNumeral = new RomanToNumeral();

        int i = 0, xNum = 0, solNum = 0;
        char c;
        String xRom, solRom;

        while (i < ope.length()) {

            xRom = "";
            c = ope.charAt(i);
            System.out.println("c : " + c) ;

            if (c != '+' || c != '-') {
                xRom = xRom + c;
                System.out.println("xRom : " + xRom);
            }
            else {
                xNum = romanToNumeral.getNumeral(xRom);
                System.out.println("xNum : " + xNum);

                if (c == '+')
                    solNum = solNum + xNum;
                else if (c == '-')
                    solNum = solNum - xNum;
                else
                    System.out.println("Probleme op \n");
            }
            i++;

        }
        solRom = numeralToRoman.getRoman(solNum);
        return solRom;
    }
}