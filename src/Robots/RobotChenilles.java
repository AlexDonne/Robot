package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;
import Robots.StrategieDeplacement.IStrategieDeplacement;

/**
 * Classe représentant le robot à chenille
 */
public class RobotChenilles extends AbstractRobot {

    public RobotChenilles(Case position, IStrategieDeplacement strategieDeplacement) {
        super(position, strategieDeplacement);
        this.reservoir = 2000;
        this.vitesse = 60000;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 2000;
    }

    /**
     * Contrôle la vitesse qui est attribuée
     * @param vitesse
     */
    @Override
    public void setVitesse(int vitesse) {
        if (vitesse > 80000) {
            this.vitesse = 80000;
        } else {
            this.vitesse = vitesse;
        }
    }

    @Override
    public TypesRobot getType() {
        return TypesRobot.CHENILLES;
    }

    @Override
    public int getVitesse(NatureTerrain natureTerrain) {
        if (natureTerrain == NatureTerrain.FORET) {
            return this.vitesse / 2;
        }

        return this.vitesse;
    }

    @Override
    double getTempsOperation(int vol) {
        return vol * 0.08;
    }
}
