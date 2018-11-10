package Environnement;

import Robots.AbstractRobot;

public class Carte {

    /**
     * Taille des cases en mètres
     */
    private int tailleCases;

    private int nbLignes;

    private int nbColonnes;

    private Case[][] cases;

    public Carte(int nbLignes, int nbColonnes, int tailleCases) {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.tailleCases = tailleCases;
        this.cases = new Case[nbLignes][nbColonnes];
    }

    public void ajouterCase(int ligne, int colonne, NatureTerrain nature) {
        cases[ligne][colonne] = new Case(nature, ligne, colonne);
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getTailleCases() {
        return tailleCases;
    }

    public Case getCase(int ligne, int colonne) {
        return cases[ligne][colonne];
    }
  

    /**
     * Regarde si une case voisine contient de l'eau
     *
     * @param src
     * @param dir
     * @return
     */
    private boolean voisinExisteEau(Case src, Direction dir) {
        switch (dir) {
            case NORD:
                return src.getLigne() != 0 && this.getCase(src.getLigne() - 1, src.getColonne()).getNatureTerrain().equals(NatureTerrain.EAU);

            case EST:
                return src.getColonne() != nbColonnes - 1 && this.getCase(src.getLigne(), src.getColonne() + 1).getNatureTerrain().equals(NatureTerrain.EAU);

            case SUD:
                return src.getLigne() != nbLignes - 1 && this.getCase(src.getLigne() + 1, src.getColonne()).getNatureTerrain().equals(NatureTerrain.EAU);

            case OUEST:
                return src.getColonne() != 0 && this.getCase(src.getLigne(), src.getColonne() - 1).getNatureTerrain().equals(NatureTerrain.EAU);

            default:
                throw new IllegalArgumentException("Direction n'existe pas");
        }
    }

    /**
     * Retourne true si le robot est à côté d'une case contenant de l'eau
     *
     * @param position
     * @return
     */
    public boolean eauAdjacente(Case position) {
        return this.voisinExisteEau(position, Direction.NORD)
                || this.voisinExisteEau(position, Direction.EST)
                || this.voisinExisteEau(position, Direction.OUEST)
                || this.voisinExisteEau(position, Direction.SUD);
    }
}
