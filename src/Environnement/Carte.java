package Environnement;

import Robots.AbstractRobot;

public class Carte {
    private double tailleCases;

    private int nbLignes;

    private int nbColonnes;

    private Case[][] cases;

    public Carte(int nbLignes, int nbColonnes, double tailleCases) {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.tailleCases = tailleCases;
        this.cases = new Case[nbLignes][nbColonnes];
    }

    public void ajouterCase(int ligne, int colonne, NatureTerrain nature) {
        cases[ligne][colonne] = new Case(nature, ligne, colonne);
    }

    public double getTailleCases() {
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


    public Graphe toGraphe(AbstractRobot robot) {
        Graphe graphe = new Graphe(this.nbLignes * this.nbColonnes);
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 1; j < nbColonnes; j++) {
                if (robot.getType().getDeplacements().contains(this.cases[i][j-1].getNatureTerrain()) && robot.getType().getDeplacements().contains(this.cases[i][j].getNatureTerrain())) {
                    double temps = 0;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i][j - 1].getNatureTerrain()) / 2;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i][j].getNatureTerrain()) / 2;
                    graphe.ajouterArc(i * nbColonnes + j - 1, i * nbColonnes + j, temps);
                }
            }
        }
        for (int j = 0; j < nbColonnes; j++) {
            for (int i = 1; i < nbLignes; i++) {
                if (robot.getType().getDeplacements().contains(this.cases[i-1][j].getNatureTerrain()) && robot.getType().getDeplacements().contains(this.cases[i][j].getNatureTerrain())) {
                    double temps = 0;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i-1][j].getNatureTerrain()) / 2;
                    temps += this.tailleCases / robot.getVitesse(this.cases[i][j].getNatureTerrain()) / 2 / 2;
                    graphe.ajouterArc((i - 1) * nbColonnes + j, i * nbColonnes + j, temps);
                }
            }
        }
        return graphe;
    }

    public boolean eauAdjacente(Case position){
        int i = position.getLigne();
        int j = position.getColonne();
        if (0 < i && i < this.nbLignes - 1) {
            if (0 < j && j < this.nbColonnes - 1) {
                return this.cases[i-1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i+1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j+1].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j-1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
            if (j == 0) {
                return this.cases[i-1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i+1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j+1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
            if (j == this.nbColonnes - 1) {
                return this.cases[i-1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i+1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j-1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
        }
        if (i == 0) {
            if (0 < j && j < this.nbColonnes - 1) {
                return this.cases[i+1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j+1].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j-1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
            if (j == 0) {
                return this.cases[i+1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j+1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
            if (j == this.nbColonnes - 1) {
                return this.cases[i+1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j-1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
        }
        if (i == this.nbLignes - 1) {
            if (0 < j && j < this.nbColonnes - 1) {
                return this.cases[i-1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j+1].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j-1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
            if (j == 0) {
                return this.cases[i-1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j+1].getNatureTerrain().equals(NatureTerrain.EAU) ;
            }
            if (j == this.nbColonnes - 1) {
                return this.cases[i-1][j].getNatureTerrain().equals(NatureTerrain.EAU)
                        || this.cases[i][j-1].getNatureTerrain().equals(NatureTerrain.EAU);
            }
        }
        throw new IllegalArgumentException("Case hors carte");
    }
}