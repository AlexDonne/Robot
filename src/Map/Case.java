package Map;

import Utils.NatureTerrain;

public class Case {
    private int ligne;

    private int colonne;

    private NatureTerrain natureTerrain;

    public Case(NatureTerrain natureTerrain){
        this.natureTerrain = natureTerrain;
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
