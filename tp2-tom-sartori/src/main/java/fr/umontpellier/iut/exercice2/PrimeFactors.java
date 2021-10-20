package fr.umontpellier.iut.exercice2;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public List<Integer> computeFactors(int number) {

        ArrayList List = new ArrayList();
        int[] nbPrem = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int i = 0, produit = 1;

        while (number != 1) {
            if (number % nbPrem[i] == 0) {
                List.add(nbPrem[i]);
                number = number / nbPrem[i];
            } else
                i++;
        }

        return List;
    }
}