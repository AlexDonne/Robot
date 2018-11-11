package Decision;

import Environnement.Carte;
import Environnement.Incendie;
import Robots.AbstractRobot;
import io.Simulateur;

import java.util.List;

/**
 * Strategie simple
 */
public class StrategieGlobaleSimple implements IStrategieGlobale {

    /**
     * Parcourt les incendies et les assigne à des robots libres
     */
    @Override
    public void prendreDecisions(Simulateur simulateur, List<Incendie> incendies, List<AbstractRobot> robots, Carte carte) {
        for (Incendie incendie : incendies) {
            if (incendie.estPrisEnCharge()) {
                continue;
            }
            for (AbstractRobot robot : robots) {
                if (robot.isOccupe()) {
                    continue;
                }
                if (robot.seChargeDeLincendie(carte, incendie, simulateur)) {
                    break;
                }
            }
        }
    }
}
