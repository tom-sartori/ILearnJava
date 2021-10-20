package fr.umontpellier.iut.exo2;

public class Note {
    private Matiere matiere;
    private double valeur;

    public Note(Matiere matiere, double valeur) {
        this.matiere = matiere;
        this.valeur = valeur;
    }

    public Matiere getMatiere () {
        return this.matiere;
    }

    public double getValeur() {
        return valeur;
    }
}
