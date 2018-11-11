package Robots.StrategieDeplacement;

import Environnement.*;
import Robots.AbstractRobot;
import Robots.TypesRobot;
import Exception.CheminNonExistantException;

import java.util.*;

/**
 * Classe représentant un graphe, sert pour la stratégie dijkstra.
 */
public class Graphe {
    private int nombreSommets; //nombre de sommets
    private double[][] matriceAdjacence;

    /**
     * Constructeur appelé seulement au sein de la classe
     * @param n
     */
    private Graphe(int n) {
        this.nombreSommets = n;
        this.matriceAdjacence = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matriceAdjacence[i][j] = 0;
                } else {
                    matriceAdjacence[i][j] = -1;
                }
            }
        }
    }

    /**
     * Pour un robot donné, transforme la carte en objet graphe, méthode static qui créé l'objet Graphe
     *
     * @param robot
     * @return
     */
    public static Graphe creerGraphe(Carte carte, AbstractRobot robot) {
        Graphe graphe = new Graphe(carte.getNbLignes() * carte.getNbColonnes());
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 1; j < carte.getNbColonnes(); j++) {
                if (robot.getType().getDeplacements().contains(carte.getCase(i, j - 1).getNatureTerrain()) && robot.getType().getDeplacements().contains(carte.getCase(i, j).getNatureTerrain())) {
                    double temps = 0;
                    temps += ((float) carte.getTailleCases()) / robot.getVitesse(carte.getCase(i, j - 1).getNatureTerrain()) / 2;
                    temps += ((float) carte.getTailleCases()) / robot.getVitesse(carte.getCase(i, j).getNatureTerrain()) / 2;
                    temps *= 3600; //Pour convertir en secondes
                    graphe.ajouterArc(i * carte.getNbColonnes() + j - 1, i * carte.getNbColonnes() + j, temps);
                }
            }
        }
        for (int j = 0; j < carte.getNbColonnes(); j++) {
            for (int i = 1; i < carte.getNbLignes(); i++) {
                if (robot.getType().getDeplacements().contains(carte.getCase(i - 1, j).getNatureTerrain()) && robot.getType().getDeplacements().contains(carte.getCase(i, j).getNatureTerrain())) {
                    double temps = 0;
                    temps += ((float) carte.getTailleCases()) / robot.getVitesse(carte.getCase(i - 1, j).getNatureTerrain()) / 2;
                    temps += ((float) carte.getTailleCases()) / robot.getVitesse(carte.getCase(i, j).getNatureTerrain()) / 2;
                    temps *= 3600; //Pour convertir en secondes
                    graphe.ajouterArc((i - 1) * carte.getNbColonnes() + j, i * carte.getNbColonnes() + j, temps);
                }
            }
        }
        return graphe;
    }

    /**
     * ajoute l'arc entre les sommets i et j au graphe
     *
     * @param i
     * @param j
     * @param val
     */
    public void ajouterArc(int i, int j, double val) {
        this.matriceAdjacence[i][j] = val;
        this.matriceAdjacence[j][i] = val;
    }

    private static int marquerSommetSuivant(int[] sommetPrec, double[] distance, boolean[] estMarque, int n) throws CheminNonExistantException {
        int i = 0;
        while ((i < n) && (estMarque[i] || distance[i] < 0)) {
            i++;
        }
        if (i == n) {
            throw new CheminNonExistantException("Plus de sommet à marquer. Le chemin n'existe pas !");
        }
        int k = i;
        double distMin = distance[i];
        for (int j = i + 1; j < n; j++) {
            if (!estMarque[j] && 0 <= distance[j] && distance[j] < distMin) {
                k = j;
                distMin = distance[j];
            }
        }
        estMarque[k] = true;
        return k;
    }

    private void mise_a_jour(int[] sommetPrec, double[] distance, boolean[] estMarque, double val, int sommet) {
        for (int j = 0; j < this.nombreSommets; j++) {
            if (!estMarque[j]) {
                double val1 = this.matriceAdjacence[sommet][j];
                if (val1 >= 0) {
                    if (distance[j] < 0) {
                        distance[j] = val1 + val;
                        sommetPrec[j] = sommet;
                    } else {
                        if (val + val1 < distance[j]) {
                            distance[j] = val + val1;
                            sommetPrec[j] = sommet;
                        }
                    }
                }
            }
        }
    }

    /**
     * Retourne le plus court itinéraire entre 2 cases
     *
     * @param depart
     * @param arrivee
     * @param carte
     * @return
     * @throws Exception
     */
    public Itineraire dijkstra(Case depart, Case arrivee, Carte carte) throws CheminNonExistantException {
        int a = depart.getLigne() * carte.getNbColonnes() + depart.getColonne();
        int b = arrivee.getLigne() * carte.getNbColonnes() + arrivee.getColonne();
        int[] sommetPrec = new int[this.nombreSommets]; // donne pour chaque sommet le sommet par lequel il est atteint
        double[] distance = new double[this.nombreSommets]; // donne pour chaque sommet sa distance au sommet a
        boolean[] estMarque = new boolean[this.nombreSommets]; // indique pour chaque sommet si le sommet est marque

        // initialisation
        for (int i = 0; i < this.nombreSommets; i++) {
            sommetPrec[i] = -1;
            distance[i] = -1;
            estMarque[i] = false;
        }
        sommetPrec[a] = a;
        distance[a] = 0;
        estMarque[a] = true;

        // corps de l'algorithme
        int c = a;
        double val = 0;

        while (c != b) {
            this.mise_a_jour(sommetPrec, distance, estMarque, val, c);
            c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.nombreSommets);
            val = distance[c];
        }

        List<ElementItineraire> mapItineraire = new ArrayList<ElementItineraire>();
        int sommet = b;
        double dist = val;

        while (sommet != a) {
            mapItineraire.add(new ElementItineraire(carte.getCase(sommet / carte.getNbColonnes(), sommet % carte.getNbColonnes()), dist));
            sommet = sommetPrec[sommet];
            dist = distance[sommet];
        }

        return new Itineraire(mapItineraire);
    }

    /**
     * Retourne le plus court itineraire qui mène à une case d'eau
     *
     * @param depart
     * @param carte
     * @param robot
     * @return
     * @throws Exception
     */
    public Itineraire dijkstraEau(Case depart, Carte carte, AbstractRobot robot) throws CheminNonExistantException { // donne l'itinéraire pour aller à la case adjacente à de l'eau la plus proche
        int a = depart.getLigne() * carte.getNbColonnes() + depart.getColonne();
        int[] sommetPrec = new int[this.nombreSommets]; // donne pour chaque sommet le sommet par lequel il est atteint
        double[] distance = new double[this.nombreSommets]; // donne pour chaque sommet sa distance au sommet a
        boolean[] estMarque = new boolean[this.nombreSommets]; // indique pour chaque sommet si le sommet est marque

        // initialisation
        for (int i = 0; i < this.nombreSommets; i++) {
            sommetPrec[i] = -1;
            distance[i] = -1;
            estMarque[i] = false;
        }
        sommetPrec[a] = a;
        distance[a] = 0;
        estMarque[a] = true;

        int c = a;
        Case inter = depart;
        double val = 0;
        if (robot.getType().equals(TypesRobot.DRONE)) {
            while (!inter.getNatureTerrain().equals(NatureTerrain.EAU)) {
                this.mise_a_jour(sommetPrec, distance, estMarque, val, c);
                c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.nombreSommets);
                val = distance[c];
                inter = carte.getCase(c / carte.getNbColonnes(), c % carte.getNbColonnes());
            }
        } else {
            while (!carte.eauAdjacente(inter)) {
                this.mise_a_jour(sommetPrec, distance, estMarque, val, c);
                c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.nombreSommets);
                val = distance[c];
                inter = carte.getCase(c / carte.getNbColonnes(), c % carte.getNbColonnes());
            }
        }
        // On retourne enfin la liste des sommets par lesquels passer
        List<ElementItineraire> mapItineraire = new ArrayList<ElementItineraire>();
        int sommet = c;
        double dist = val;

        while (sommet != a) {
            mapItineraire.add(new ElementItineraire(carte.getCase(sommet / carte.getNbColonnes(), sommet % carte.getNbColonnes()), dist));
            sommet = sommetPrec[sommet];
            dist = distance[sommet];
        }

        return new Itineraire(mapItineraire);
    }
}
