package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;
import Robots.StrategieDeplacement.IStrategieDeplacement;

/**
 * Classe représentant le robot à pattes
 */
public class RobotPattes extends AbstractRobot {

    public RobotPattes(Case position, IStrategieDeplacement strategieDeplacement) {
        super(position, strategieDeplacement);
        this.reservoir = -1; //Infini
        this.vitesse = 30000;
    }

    /**
     * Fonction pas appelée pour ce type de robot, mais qui doit être redéfinie
     */
    @Override
    public void remplirReservoir() {
    }

    /**
     * Volume ne diminue pas
     *
     * @param vol, le volume déversé
     */
    @Override
    public void deverserEauSurIncendie(int vol) {
    }

    @Override
    public TypesRobot getType() {
        return TypesRobot.PATTES;
    }

    @Override
    public int getVitesse(NatureTerrain natureTerrain) {
        if (natureTerrain == NatureTerrain.ROCHE) {
            return 10000;
        }
        return this.vitesse;
    }

    @Override
    double getTempsOperation(int vol) {
        return vol * 0.1;
    }
}
