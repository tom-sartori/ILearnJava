package fr.umontpellier.iut.exercice1;

public class FizzBuzz {

    public String getValue(int i) {
        String s = "";

        if (i % 3 == 0)
            s = "Fizz";
        if (i % 5 == 0)
            s = s + "Buzz";
        if (i%3 != 0 && i%5 != 0)
            s = s +i;
        return s;
    }

    public String[] computeList(int i) {
        String [] result = new String[i+1];

        for (int j = 0; j < i; j++)
            result[j] = getValue(j+1);

        return result;
    }
}
