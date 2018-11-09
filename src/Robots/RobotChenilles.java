package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;

public class RobotChenilles extends AbstractRobot {
    public RobotChenilles(Case position) {
        super(position);
        this.reservoir = 2000;
        this.vitesse = 60000;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 2000;
    }

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
        return vol / 750;
    }
}
