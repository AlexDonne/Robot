package Robots.StrategieDeplacement;

import Environnement.Carte;
import Environnement.Case;
import Environnement.Itineraire;
import Robots.AbstractRobot;

/**
 * Itineraire créé grâce à l'algorithme de dijkstra
 */
public class StrategieDeplacementDijkstra implements IStrategieDeplacement {

    @Override
    public Itineraire creerItineraire(AbstractRobot robot, Case arrivee, Carte carte) throws Exception {
        Graphe graphe = Graphe.creerGraphe(carte, robot);
        return graphe.dijkstra(robot.getPosition(), arrivee, carte);
    }

    @Override
    public Itineraire creerItineraireEau(AbstractRobot robot, Carte carte) throws Exception {
        Graphe graphe = Graphe.creerGraphe(carte, robot);
        return graphe.dijkstraEau(robot.getPosition(), carte, robot);
    }
}
