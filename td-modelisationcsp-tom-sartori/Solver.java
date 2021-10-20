// Probl�me des N_reines
// SARTORI Tom

public class Solver {

    public static void main(String[] args) {
        /*
        int [] tab = new int[13];
        int nMax = 4;
        solve(tab, nMax);
        System.out.println("Résultat : " + toString(tab));
        */
        Solver s = new Solver();
        s.solve2(0);
        System.out.println(toString(tab));
    }

    public static int [] tab = new int[4];
    public static int nb = 4;

    public boolean solve2 (int i) {
        if (i == tab.length)
            return true;

        for (int j = 1; j <= nb; j++) {
            tab[i] = j;
            if (checkContraintes(tab)) {
                boolean test = solve2(i + 1);
                if (!test) {
                    tab [i + 1] = 0;
                }
                else
                    return true;
            }
        }
        return false;
    }

    public static void solve (int [] tab, int nMax) {
        solveAux(tab, nMax, 0, -1);
    }

    public static boolean solveAux (int [] tab, int nMax, int i, int oldI) {

        if (i == tab.length) {   // Solution trouvée
            return true;
        }
        else if (i == -1) {  // Pas de solution
            return false;
        }
        else {  // Cas normal

            if (oldI < i) { // On vient de monter
                if (tab[i] == 0)
                    tab[i] = 1;
            }
            else if (i < oldI) { // On vient de descendre
                tab[oldI] = 0;
                if (tab[i] == nMax) {  // Retour arriere
                    solveAux(tab, nMax, i - 1, i);
                }
                else {  // On augmente la case;
                    tab[i]++;
                }
            }


            while (!checkContraintes2(tab) && tab[i] < nMax) { // On augmente la case i
                tab[i]++;
            }

            if (checkContraintes2(tab)) { // Case validée, on passe à la suite
                solveAux(tab, nMax, i + 1, i);
            }
            else {  // tab[i] == n && checkContraintes(tab) == false // Donc retour à la case d'avant
                solveAux(tab, nMax, i - 1, i);
            }

        }
        return true;
    }


    // Return true si tab respecte les contraintes du jeu.
    // Ne prend pas en compte les 0.
    public static boolean checkContraintes(int[] tab) { // Ne prend pas en compte les cases à 0.
        return allDifferent(tab) && checkDiag(tab);
    }

    // Return true si les valeurs du tableau sont toutes différentes.
    // Ne prend pas en compte les 0.
    public static boolean allDifferent (int [] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = i+1; j < tab.length; j++)
                if (tab[i] != 0 && tab[j] != 0 && i != j)
                    if (tab[i] == tab[j])
                        return false;
        }
        return true;
    }

    // Return true si respecte la condition sur les diagonales.
    // Ne prend pas en compte les 0.
    public static boolean checkDiag(int [] tab) {
        for (int i = 0; i < tab.length; i++)
            for (int j = i + 1; j < tab.length; j++) {
                if (tab[i] != 0 && tab[j] != 0 && i != j)
                    if (Math.abs(tab[i] - tab[j]) == Math.abs(i - j))
                        return false;
            }
        return true;
    }

    public static boolean checkContraintes2 (int [] tab) {
        boolean check = false;

        if (tab[0] == 0)
            return check;
        check = tab[0] != tab[1] && tab [0] != tab[7];

        if (!check || tab[1] == 0)
            return check;
        check = tab[1] != tab[2] && tab[1] != tab[3] && tab[1] != tab[6] && tab[1] != tab[7];

        if (!check || tab[2] == 0)
            return check;
        check = tab[2] != tab[3] && tab[2] != tab[4];

        if (!check || tab[3] == 0)
            return check;
        check = tab[3] != tab[4] && tab[3] != tab[5] && tab[3] != tab[6];

        if (!check || tab[4] == 0)
            return check;
        check = tab[4] != tab[5];

        if (!check || tab[5] == 0)
            return check;
        check = tab[5] != tab[6] && tab[5] != tab[9];

        if (!check || tab[6] == 0)
            return check;
        check = tab[6] != tab[7] && tab[6] != tab[8] && tab[6] != tab[9];

        if (!check || tab[7] == 0)
            return check;
        check = tab[7] != tab[8];

        if (!check || tab[8] == 0)
            return check;
        check = tab[8] != tab[9] && tab[8] != tab[10];

        if (!check || tab[9] == 0)
            return check;
        check = tab[9] != tab[10] && tab[9] != tab[11];

        if (!check || tab[10] == 0)
            return check;
        check = tab[10] != tab[11];

        if (!check || tab[11] == 0)
            return check;
        check = tab[11] != tab[12];

        return check;
    }


    public  static String toString (int [] tab) {
        String s = "[ ";
        for (int i = 0; i < tab.length - 1; i++)
            s += tab[i] + ", ";
        s += tab[tab.length - 1] + " ]";
        return s;
    }
}
