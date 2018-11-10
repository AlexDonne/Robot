package Decision;

import Environnement.Carte;
import Environnement.Incendie;
import Robots.AbstractRobot;
import io.Simulateur;

import java.util.List;

/**
 * Interface à implémenter pour les différentes stratégies que peut adopter le chef pompier
 */
public interface IStrategieGlobale {
    void prendreDecisions(Simulateur simulateur, List<Incendie> incendies, List<AbstractRobot> robots, Carte carte);
}
