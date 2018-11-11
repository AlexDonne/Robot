package Robots;

import Environnement.Case;
import Robots.StrategieDeplacement.IStrategieDeplacement;

/**
 * Classe représentant le robot drone
 */
public class RobotAerien extends AbstractRobot {

    public RobotAerien(Case position, IStrategieDeplacement strategieDeplacement) {
        super(position, strategieDeplacement);
        this.reservoir = 10000;
        this.vitesse = 100000;
    }

    /**
     * Redéfinit le setVitesse, ne peut déplacer les 150000m/h
     *
     * @param vitesse
     */
    @Override
    public void setVitesse(int vitesse) {
        if (vitesse > 150000) {
            this.vitesse = 150000;
        } else {
            this.vitesse = vitesse;
        }
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 10000;
    }

    @Override
    public TypesRobot getType() {
        return TypesRobot.DRONE;
    }

    @Override
    double getTempsOperation(int vol) {
        return 30;
    }
}
