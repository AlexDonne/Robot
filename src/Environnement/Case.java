package Environnement;

public class Case {
    private int ligne;

    private int colonne;

    private NatureTerrain natureTerrain;

    public Case(NatureTerrain natureTerrain, int ligne, int colonne)
    {
        this.natureTerrain = natureTerrain;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public NatureTerrain getNatureTerrain() {
        return natureTerrain;
    }
}
