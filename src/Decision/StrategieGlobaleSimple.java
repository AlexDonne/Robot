package Decision;

import Environnement.Carte;
import Environnement.Incendie;
import Robots.AbstractRobot;
import io.Simulateur;

import java.util.List;

public class StrategieGlobaleSimple implements IStrategieGlobale {

    @Override
    public void prendreDecisions(Simulateur simulateur, List<Incendie> incendies, List<AbstractRobot> robots, Carte carte) {
        /**
         * Parcourt les incendies et les assigne à des robots libres
         */
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
