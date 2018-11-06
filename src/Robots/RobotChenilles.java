package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;

public class RobotChenilles extends RobotTerrestre {
    public RobotChenilles(Case position) {
        super(position);
        this.reservoir = 2000;
        this.vitesse = 60;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 2000;
    }

    @Override
    public void setVitesse(int vitesse) {
        if (vitesse > 80) {
            this.vitesse = 80;
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
}
