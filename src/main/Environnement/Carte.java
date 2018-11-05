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
    }

    public void ajouterCase(int ligne, int colonne, Case position) {
        cases[ligne][colonne] = position;
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

    public void afficher() {
        System.out.println("Voci la carte:");
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                System.out.print(this.cases[i][j].getNatureTerrain().toString());
                System.out.print(" ; ");
            }
            System.out.println();
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
}