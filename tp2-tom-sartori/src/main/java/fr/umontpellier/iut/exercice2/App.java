package fr.umontpellier.iut.exercice2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("veuillez saisir un nombre :");
        Scanner keyboard = new Scanner(System.in);
        int number = keyboard.nextInt();
        PrimeFactors primeFactors = new PrimeFactors();
        System.out.println(primeFactors.computeFactors(number));
    }

}
