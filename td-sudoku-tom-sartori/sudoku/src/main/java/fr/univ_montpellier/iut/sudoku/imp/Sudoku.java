package fr.univ_montpellier.iut.sudoku.imp;

import java.sql.SQLOutput;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sudoku {

	int n;
	int s;
	int[][] grid;

	/*
	 * Create an instance of the problem sudoku (nxn)
	 *
	 */
	public Sudoku(int n) {
		this.n = n;
		this.s = (int) Math.sqrt(n);
		this.grid = new int[n][n];
	}


	/*
	 * check if this.grid is a correct sudoku solution.
	 *
	 */
	private boolean solutionChecker() {
		return checkAllValeurs() && checkAllRow(true) && checkAllColumn(true) && checkAllSquares(true);
	}

	// Check la solution actuelle sans prendre en compte les "0" de la grille.
	private boolean checkActualSolution() {
		return checkAllRow(false) && checkAllColumn(false) && checkAllSquares(false);
	}


	// Si checkVoid false, alors on ne prend pas en compte les cases vides ("0") de la grille.
	private boolean checkRow(int i, boolean checkVoid) {
		int check;
		for (int j = 0; j < n; j++) {
			check = grid[i][j];
			for (int k = j; k < n; k++) {
				if (j != k && grid[i][k] == check) {
					if (checkVoid)
						return false;
					else if (grid[i][k] != 0)
						return false;
				}
			}
		}
		return true;
	}

	private boolean checkAllRow(boolean checkVoid) {
		for (int i = 0; i < n; i++)
			if(!checkRow(i, checkVoid))
				return false;
		return true;
	}


	// Si checkVoid false, alors on ne prend pas en compte les cases vides ("0") de la grille.
	private boolean checkColumn(int j, boolean checkVoid) {
		int check;
		for (int i = 0; i < n; i++) {
			check = grid[i][j];
			for (int k = j; k < n; k++) {
				if (i != k && grid[k][j] == check) {
					if (checkVoid)
						return false;
					else if (grid[k][j] != 0)
						return false;
				}
			}
		}
		return true;
	}

	private boolean checkAllColumn(boolean checkVoid) {
		for (int i = 0; i < n; i++)
			if(!checkColumn(i, checkVoid))
				return false;
		return true;
	}


	// Si checkVoid false, alors on ne prend pas en compte les cases vides ("0") de la grille.
	private boolean checkSquare(int iStart, int jStart, boolean checkVoid) {
		for(int i = 0; i < n; i++)
			for(int j = i + 1; j < n; j++) {
				if (grid[iStart + i % s][jStart + i / s] == grid[iStart + j % s][jStart + j / s]) {
					if (checkVoid)
						return false;
					else if (grid[iStart + i % s][jStart + i / s] != 0)
						return false;
				}

				/*
				if (checkVoid) {
					if (grid[iStart + i % s][jStart + i / s] == grid[iStart + j % s][jStart + j / s]) {
						return false;
					}
				}
				else {
					if (grid[iStart + i % s][jStart + i / s] != 0 && grid[iStart + i % s][jStart + i / s] == grid[iStart + j % s][jStart + j / s]) {
						return false;
					}
				}

				 */

			}
		return true;
	}

	private boolean checkAllSquares (boolean checkVoid) {
		for(int i = 0; i < n; i += s)
			for(int j = 0; j < n; j += s)
				if (!checkSquare(i, j, checkVoid))
					return false;
		return true;
	}

	// Renvoie faux si une des valeurs de la grille n'est pas comprise entre 1 et n.
	private boolean checkAllValeurs () {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j ++)
				if (grid[i][j] <= 0 || grid[i][j] > n)
					return false;
		return true;
	}

	/*
	 * Generate a random grid solution
	 *
	 */
	private void generateSolution() {
		Random random = new Random();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				grid[i][j] = random.nextInt(n) + 1;
	}

	// Genere une solution aléatoire avec des lignes justes.
	private void generateSolutionV2() {
		List val = new ArrayList();
		Random random = new Random();
		int index;

		for (int i = 0; i < n; i++) {
			val.clear();
			for (int k = 1; k <= n; k ++)
				val.add(k);

			for (int j = 0; j < n; j++) {
				index = random.nextInt(val.size());
				grid[i][j] = (int)val.get(index);
				val.remove(index);
			}
		}
	}

	// Genere une solution valable. Réponse de la question 6. Il reste une partie d'aléatoire, mais pas de récursif.
	private void generateSolutionV3() {
		Random random = new Random();
		int nbTour;

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				nbTour = 0;
				do {
					if (nbTour > n) {	// Si on a deja essayé n valeurs aléatoires pour une meme case, alors on la réinitialise et on passe à la précédente.
						grid[i][j] = 0;
						if (j == 0) {
							if (i != 0)
								i--;
							j = n - 1;
						}
						else
							j--;
					}
					nbTour++;

					grid[i][j] = random.nextInt(n) + 1;	// On choisi une valeur aléatoire entre 1 et n pour la case correspondante.
				}
				while (!checkActualSolution());	// Tant que la valeur de la dernière case n'est pas valide, on en calcule une nouvelle.
			}
	}


	/*
	 * Find a solution to the sudoku problem
	 *
	 */
	public void findSolution() {
		long temps = System.currentTimeMillis();
		//int n = 0;
		do {
			// generateSolution();
			generateSolutionV3();
			//n++;
		}
		while (!solutionChecker());
		System.out.println("Temps necessaire : " + (System.currentTimeMillis() - temps) + "ms");
		//System.out.println("Nombre d'essaies : " + n);
	}


	public static void main(String args[]) {
		//test4x4();
		//testGenererSolution();
		//testFindSol(16);
		testFindSolTemps(9);
	}

	public String toString(){
		String str = "";
		for(int i=0 ; i < n ; i++) {
			if (i%s == 0)
				str = str + "\n";
			for (int j = 0; j < n; j++) {
				if (j%s == 0)
					str = str + "   ";
				str = str + grid[i][j] + " ";
			}
			str = str + "\n";
		}
		return str;
	}


	private static void test4x4() {
		Sudoku sudoku = new Sudoku(4);
		sudoku.grid[0][0] = 2; // 2
		sudoku.grid[0][1] = 1; // 1
		sudoku.grid[0][2] = 3; // 3
		sudoku.grid[0][3] = 4; // 4

		sudoku.grid[1][0] = 4; // 4
		sudoku.grid[1][1] = 3; // 3
		sudoku.grid[1][2] = 1; // 1
		sudoku.grid[1][3] = 2; // 2

		sudoku.grid[2][0] = 1; // 1
		sudoku.grid[2][1] = 2; // 2
		sudoku.grid[2][2] = 4; // 4
		sudoku.grid[2][3] = 3; // 3

		sudoku.grid[3][0] = 3; // 3
		sudoku.grid[3][1] = 4; // 4
		sudoku.grid[3][2] = 2; // 2
		sudoku.grid[3][3] = 1; // 1

		System.out.println(sudoku.solutionChecker());
		System.out.println(sudoku);
	}

	public static void testGenererSolution() {
		Sudoku sudoku = new Sudoku(9);
		sudoku.generateSolution();
		System.out.println(sudoku);
	}

	public static void testFindSol(int n) {
		Sudoku sudoku = new Sudoku(n);
		sudoku.findSolution();
		System.out.println(sudoku);
	}

	public static void testFindSolTemps (int n) {
		long depart, fin, diff;
		long total = 0;
		for (int i = 0; i < 100; i ++ ) {
			Sudoku sudoku = new Sudoku(n);
			depart = System.currentTimeMillis();
			sudoku.findSolution();
			fin = System.currentTimeMillis();
			diff = fin - depart;
			total = total + diff;
		}
		System.out.println("Temps moyen pour 100 sudoku de taille " + n + " : " + (total/100) + "ms");
	}
}
