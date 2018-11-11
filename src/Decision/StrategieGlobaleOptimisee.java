package Decision;

import Environnement.Carte;
import Environnement.Case;
import Environnement.Incendie;
import Robots.AbstractRobot;
import Robots.StrategieDeplacement.TempsDeplacementComparator;
import io.Simulateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategie optimisee, le chef pompier confie l'incendie au robot qui arrivera en le moins de temps
 */
public class StrategieGlobaleOptimisee implements IStrategieGlobale {
    @Override
    public void prendreDecisions(Simulateur simulateur, List<Incendie> incendies, List<AbstractRobot> robots, Carte carte) {
        List<AbstractRobot> robotsClones = new ArrayList<>(robots);
        for (Incendie incendie : incendies) {
            if (incendie.estPrisEnCharge()) {
                continue;
            }
            robotsClones.removeIf(robot -> robot.isOccupe());
            if (robotsClones.isEmpty()) {
                break;
            }
            AbstractRobot plusProche = this.robotPlusProche(carte, robotsClones, incendie.getPosition());
            if (plusProche.tempsDeplacement(incendie.getPosition(), carte) != -1) {
                plusProche.seChargeDeLincendie(carte, incendie, simulateur);
            }
        }
    }

    /**
     * Retourne le robot le plus proche de l'incendie
     * @param carte
     * @param robots
     * @param arrivee
     * @return
     */
    private AbstractRobot robotPlusProche(Carte carte, List<AbstractRobot> robots, Case arrivee) {
        robots.sort(new TempsDeplacementComparator(arrivee, carte));
        return robots.get(0);
    }
}
