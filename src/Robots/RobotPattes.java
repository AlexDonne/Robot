package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;

public class RobotPattes extends AbstractRobot {
    public RobotPattes(Case position) {
        super(position);
        this.reservoir = -1; //Infini
        this.vitesse = 30000;
    }

    @Override
    public void remplirReservoir() {

    }

    @Override
    public double deverserEauSurIncendie(int vol) {
        return this.getTempsOperation(vol);
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
        return vol / 600;
    }
}
