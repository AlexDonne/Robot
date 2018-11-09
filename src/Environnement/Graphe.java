package Environnement;

import Robots.AbstractRobot;
import Robots.TypesRobot;

import java.util.*;

public class Graphe {
    private int n; //nombre de sommets
    private double[][] matriceAdjacence;

    public Graphe(int n) {
        this.n = n;
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
     * ajoute l'arc entre les sommets i et j au graphe
     * @param i
     * @param j
     * @param val
     */
    public void ajouterArc(int i, int j, double val) {
        this.matriceAdjacence[i][j] = val;
        this.matriceAdjacence[j][i] = val;
    }

    private static int marquerSommetSuivant(int[] sommetPrec, double[] distance, boolean[] estMarque, int n) throws Exception {
        int i = 0;
        while ((i < n) && (estMarque[i] || distance[i] < 0)) {
            i++;
        }
        if (i == n) {
            throw new Exception("Plus de sommet à marquer. Le chemin n'existe pas !");
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
        for (int j = 0; j < this.n; j++) {
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
     * @param depart
     * @param arrivee
     * @param carte
     * @return
     * @throws Exception
     */
    public Itineraire dijkstra(Case depart, Case arrivee, Carte carte) throws Exception {
        int a = depart.getLigne() * carte.getNbColonnes() + depart.getColonne();
        int b = arrivee.getLigne() * carte.getNbColonnes() + arrivee.getColonne();
        int[] sommetPrec = new int[this.n]; // donne pour chaque sommet le sommet par lequel il est atteint
        double[] distance = new double[this.n]; // donne pour chaque sommet sa distance au sommet a
        boolean[] estMarque = new boolean[this.n]; // indique pour chaque sommet si le sommet est marque

        // initialisation
        for (int i = 0; i < this.n; i++) {
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
            c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.n);
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
     * @param depart
     * @param carte
     * @param robot
     * @return
     * @throws Exception
     */
    public Itineraire dijkstraEau(Case depart, Carte carte, AbstractRobot robot) throws Exception { // donne l'itinéraire pour aller à la case adjacente à de l'eau la plus proche
        int a = depart.getLigne() * carte.getNbColonnes() + depart.getColonne();
        int[] sommetPrec = new int[this.n]; // donne pour chaque sommet le sommet par lequel il est atteint
        double[] distance = new double[this.n]; // donne pour chaque sommet sa distance au sommet a
        boolean[] estMarque = new boolean[this.n]; // indique pour chaque sommet si le sommet est marque

        // initialisation
        for (int i = 0; i < this.n; i++) {
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
                c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.n);
                val = distance[c];
                inter = carte.getCase(c / carte.getNbColonnes(), c % carte.getNbColonnes());
            }
        } else {
            while (!carte.eauAdjacente(inter)) {
                this.mise_a_jour(sommetPrec, distance, estMarque, val, c);
                c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.n);
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
