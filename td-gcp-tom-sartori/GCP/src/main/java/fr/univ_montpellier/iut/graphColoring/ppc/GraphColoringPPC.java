package fr.univ_montpellier.iut.graphColoring.ppc;

import java.io.IOException;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import fr.univ_montpellier.iut.graphColoring.Dataset;

public class GraphColoringPPC {


	String[] regions_names = { "Bretagne", "Normandie", "HautsDeFrance", "IleDeFrance", "GrandEst",
			"BourgogneFrancheComte", "CentreValDeLoire", "PaysDeLaLoire", "NouvelleAquitaine", "AuvergneRhoneAlpes",
			"Occitanie", "PACA", "Corse" };

	String [] couleurs_names = {"bleu", "rouge", "vert", "jaune"};

	Model model;
	IntVar [] tab;
	Dataset data;
	public static long timeout = 120000; // 5 min = 300000ms

	public static void main(String[] args) throws IOException {

	}

	public void solve (String nomFichier) {
		buildModel(nomFichier);
		//model.getSolver().showStatistics();
		model.getSolver().limitTime(timeout);
		model.getSolver().solve();
	}

	public void buildModel (String nomFichier) {
		model = new Model();
		data = new Dataset(nomFichier);
		tab = new IntVar[data.nbRegions];

		for (int i = 0; i < tab.length; i++)
			tab[i] = model.intVar(1, data.nbCouleurs);

		addContraintes();
	}

	// Permet de check si tabBis est une solution valide.
	public boolean checkSolution (String nomFichier, int [] tabBis) {
		model = new Model();
		data = new Dataset(nomFichier);
		tab = new IntVar[data.nbRegions];

		for (int i = 0; i < tab.length; i++)
			tab[i] = model.intVar(tabBis[i]);

		addContraintes();

		//model.getSolver().showStatistics();
		return model.getSolver().solve();
	}

	public void addContraintes () {
		for (int i = 0; i < data.col1.size(); i++)
			model.arithm(tab[data.col1.get(i) - 1], "!=", tab[data.col2.get(i) - 1]).post();
	}

	public String toString () {
		String s = "[ ";
		for (int i = 0; i < tab.length - 1; i++)
			s += tab[i].getValue() + ", ";
		s += tab[tab.length - 1].getValue() + " ]";
		return s;
	}

	public String toStringRegions () {
		String s = "";
		for (int i = 0; i < tab.length; i ++) {
			s += regions_names[i] + " : " + couleurs_names[tab[i].getValue() - 1] + '\n';
		}
		return s;
	}
}
