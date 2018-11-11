package Robots.StrategieDeplacement;

import Environnement.Carte;
import Environnement.Case;
import Environnement.Itineraire;
import Robots.AbstractRobot;
import Exception.CheminNonExistantException;

/**
 * Interface pour les stratégies de déplacement possibles pour un robot
 */
public interface IStrategieDeplacement {
    /**
     * Créer l'itinéraire jusqu'à une autre case, lève exception si pas de chemin possible
     *
     * @param robot
     * @param arrivee
     * @param carte
     * @return
     * @throws Exception
     */
    Itineraire creerItineraire(AbstractRobot robot, Case arrivee, Carte carte) throws CheminNonExistantException;

    /**
     * Créer un itinéraire jusqu'à la case eau la plus proche
     *
     * @param robot
     * @param carte
     * @return
     * @throws Exception
     */
    Itineraire creerItineraireEau(AbstractRobot robot, Carte carte) throws CheminNonExistantException;
}
