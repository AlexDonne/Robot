package Environnement;

import Robots.AbstractRobot;

public class Carte {

    /**
     * Taille cases en mètres
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
     * Pour un robot donné, transforme la carte en objet graphe
     *
     * @param robot
     * @return
     */
    public Graphe toGraphe(AbstractRobot robot) {
        Graphe graphe = new Graphe(this.nbLignes * this.nbColonnes);
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 1; j < nbColonnes; j++) {
                if (robot.getType().getDeplacements().contains(this.cases[i][j - 1].getNatureTerrain()) && robot.getType().getDeplacements().contains(this.cases[i][j].getNatureTerrain())) {
                    double temps = 0;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i][j - 1].getNatureTerrain()) / 2;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i][j].getNatureTerrain()) / 2;
                    temps *= 60;
                    graphe.ajouterArc(i * nbColonnes + j - 1, i * nbColonnes + j, temps);
                }
            }
        }
        for (int j = 0; j < nbColonnes; j++) {
            for (int i = 1; i < nbLignes; i++) {
                if (robot.getType().getDeplacements().contains(this.cases[i - 1][j].getNatureTerrain()) && robot.getType().getDeplacements().contains(this.cases[i][j].getNatureTerrain())) {
                    double temps = 0;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i - 1][j].getNatureTerrain()) / 2;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i][j].getNatureTerrain()) / 2;
                    temps *= 60;
                    graphe.ajouterArc((i - 1) * nbColonnes + j, i * nbColonnes + j, temps);
                }
            }
        }
        return graphe;
    }

    /**
     * Retourne true si le robot est à côté d'une case contenant de l'eau
     *
     * @param position
     * @return
     */
    boolean eauAdjacente(Case position) {
        return this.voisinExisteEau(position, Direction.NORD)
                || this.voisinExisteEau(position, Direction.EST)
                || this.voisinExisteEau(position, Direction.OUEST)
                || this.voisinExisteEau(position, Direction.SUD);
    }
}