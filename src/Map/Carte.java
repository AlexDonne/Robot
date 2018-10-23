package Map;

import Utils.Direction;

public class Carte {
    private int tailleCases;

    private int nbLignes;

    private int nbColonnes;

    private Case[][] cases;

    public Carte(int nbLignes, int nbColonnes) {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
    }

    public void ajouterCase(int ligne, int colonne, Case position){
        cases[ligne][colonne] = position;
    }

    public int getTailleCases() {
        return tailleCases;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Case getCase(int ligne, int colonne) {
        return cases[ligne][colonne];
    }

    public boolean voisinExiste(Case src, Direction dir) {
        switch (dir) {
            case NORD:
                return src.getLigne() != 0;

            case EST:
                return src.getColonne() != nbColonnes - 1;

            case SUD:
                return src.getLigne() != nbLignes - 1;

            case OUEST:
                return src.getColonne() != 0;

            default:
                throw new IllegalArgumentException("Direction n'existe pas");
        }
    }
}