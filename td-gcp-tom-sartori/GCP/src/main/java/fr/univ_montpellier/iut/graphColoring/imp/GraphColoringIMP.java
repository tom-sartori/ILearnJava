package fr.univ_montpellier.iut.graphColoring.imp;

import fr.univ_montpellier.iut.graphColoring.Dataset;


public class GraphColoringIMP {


	String[] regions_names = { "Bretagne", "Normandie", "HautsDeFrance", "IleDeFrance", "GrandEst",
			"BourgogneFrancheComte", "CentreValDeLoire", "PaysDeLaLoire", "NouvelleAquitaine", "AuvergneRhoneAlpes",
			"Occitanie", "PACA", "Corse" };

	String [] couleurs_names = {"bleu", "rouge", "vert", "jaune"};

	private Dataset data;
	public int [] tab;
	public static int compteur;
	public static long timeout = 120000; // 5 min = 300000ms
	public long startTime;


	public GraphColoringIMP(String nomFichier) {
		data = new Dataset(nomFichier);
		tab = new int[data.nbRegions];
		compteur = 0;
	}

	public boolean solve () {
		compteur = 0;
		startTime = System.currentTimeMillis();
		boolean res = solver(0);
		if (!res)
			System.out.println("TIMEOUT 2 min");
		return solver(0);
	}

	public boolean solver(int i) {
		compteur++;

		if (i == tab.length)
			return true;
		if (System.currentTimeMillis() - startTime > timeout)
			return false;

		for (int j = 1; j <= data.nbCouleurs; j++) {
			tab[i] = j;
			if (verification()) {
				boolean test = solver(i + 1);
				if (!test)
					tab [i + 1] = 0;
				else
					return true;
			}
		}
		return false;
	}


	// Solver itératif
	public boolean solveIte () {
		compteur = 0;
		int i = 0;
		int oldI = -1;

		startTime = System.currentTimeMillis();

		while (true) {
			compteur++;
			if (System.currentTimeMillis() - startTime > timeout) {
				System.out.println("TIMEOUT 2 min");
				return false;
			}
			if (i == -1)
				return false;
			if (i == tab.length)
				return true;

			if (oldI < i) { // On vient de monter
				if (tab[i] == 0)
					tab[i] = 1;
			}
			else if (i < oldI) { // On vient de descendre
				tab[oldI] = 0;
				if (tab[i] == data.nbCouleurs) {  // Retour arriere
					while (tab[i] == data.nbCouleurs) {
						oldI = i;
						i--;
					}
				}
				tab[i]++;
			}


			while (!verification() && tab[i] < data.nbCouleurs) { // On augmente la case i
				tab[i]++;
			}

			if (verification()) { // Case validée, on passe à la suite
				oldI = i;
				i++;
			}
			else {  // tab[i] == n && verification(tab) == false // Donc retour à la case d'avant
				oldI = i;
				i--;
			}
		}
	}

	// Vérifie que les valeurs dans this.tab respectent bien les contraintes de fichier data.
	public boolean verification () {
		for (int i = 0; i < data.col1.size(); i++) {
			// System.out.println(tab[data.col1.get(i) - 1] + " | " + tab[data.col2.get(i) - 1]);
			if (tab[data.col1.get(i) - 1] != 0 && tab[data.col2.get(i) - 1] != 0)
				if (tab[data.col1.get(i) - 1] == tab[data.col2.get(i) - 1])
					return false;
		}
		return true;
	}

	// Verification pour le cas des régions.
	// Vérification manuelle ; sans utiliser DataSet.
	public boolean verificationManuelle() {
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


	public String toString () {
		String s = "[ ";
		for (int i = 0; i < tab.length - 1; i++)
			s += tab[i] + ", ";
		s += tab[tab.length - 1] + " ]";
		return s;
	}

	public String toStringRegions () {
		String s = "";
		for (int i = 0; i < tab.length; i ++) {
			s += regions_names[i] + " : " + couleurs_names[tab[i] - 1] + '\n';
		}
		return s;
	}
}
