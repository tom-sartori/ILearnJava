package fr.univ_montpellier.iut.graphColoring;

import fr.univ_montpellier.iut.graphColoring.imp.GraphColoringIMP;
import fr.univ_montpellier.iut.graphColoring.ppc.GraphColoringPPC;


public class App {

    public static void main( String[] args ) {

        String nomFichier = "g5.col";

        GraphColoringIMP imp = new GraphColoringIMP(nomFichier); // 2 5 6
        imp.solve();
        System.out.println(imp);

        GraphColoringPPC ppcCheck = new GraphColoringPPC();
        System.out.println("Solution imp valide ? " + ppcCheck.checkSolution(nomFichier, imp.tab));

        System.out.println("\n ----- \n");


        compteRenduRegions();
        compteRendu();
    }

    public static void compteRenduRegions () {
        String nomFichier = "regions.col";
        System.out.println("Fichier : " + nomFichier + "\n");

        System.out.println("Solution imp : \n");
        GraphColoringIMP imp = new GraphColoringIMP(nomFichier);
        long tempsImp = System.currentTimeMillis();
        imp.solve();
        System.out.println(imp.toStringRegions());
        System.out.println("Temps : " + (System.currentTimeMillis() - tempsImp) + "ms | Nb appels récursifs : " + imp.compteur);

        System.out.println("\n ----- \n");

        System.out.println("Solution itérative : \n");
        GraphColoringIMP impIte = new GraphColoringIMP(nomFichier);
        long tempsIte = System.currentTimeMillis();
        impIte.solveIte();
        System.out.println(impIte.toStringRegions());
        System.out.println("Temps : " + (System.currentTimeMillis() - tempsIte) + "ms | Nb tours de boucle : " + impIte.compteur);

        System.out.println("\n ----- \n");

        System.out.println("Solution ppc : \n");
        GraphColoringPPC ppc = new GraphColoringPPC();
        long tempsPpc = System.currentTimeMillis();
        ppc.solve(nomFichier);
        System.out.println(ppc.toStringRegions());
        System.out.println("Temps : " + (System.currentTimeMillis() - tempsPpc) + "ms ");
    }

    public static void compteRendu () {
        for (int i = 1; i < 15; i++) {
            String nomFichier = "g" + i + ".col";
            System.out.println("Fichier : " + nomFichier + "\n");

            System.out.println("Solution imp : ");
            GraphColoringIMP imp = new GraphColoringIMP(nomFichier);
            long tempsImp = System.currentTimeMillis();
            imp.solve();
            System.out.println("Temps : " + (System.currentTimeMillis() - tempsImp) + "ms | Nb appels récursifs : " + imp.compteur);

            System.out.println("");

            System.out.println("Solution itérative : ");
            GraphColoringIMP impIte = new GraphColoringIMP(nomFichier);
            long tempsIte = System.currentTimeMillis();
            impIte.solveIte();
            System.out.println("Temps : " + (System.currentTimeMillis() - tempsIte) + "ms | Nb tours de boucle : " + impIte.compteur);

            System.out.println("");

            System.out.println("Solution ppc : ");
            GraphColoringPPC ppc = new GraphColoringPPC();
            long tempsPpc = System.currentTimeMillis();
            ppc.solve(nomFichier);
            System.out.println("Temps : " + (System.currentTimeMillis() - tempsPpc) + "ms ");

            System.out.println("\n ----- \n");

        }
    }
}
