package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;

public class RobotPattes extends RobotTerrestre{
    public RobotPattes(Case position){
        super(position);
        this.reservoir = -1; //Infini
        this.vitesse = 30;
    }

    @Override
    public void remplirReservoir() {

    }

    @Override
    public TypesRobot getType() {
        return TypesRobot.PATTES;
    }

    @Override
    public int getVitesse(NatureTerrain natureTerrain) {
        if (natureTerrain == NatureTerrain.ROCHE){
            return 10;
        }
        return this.vitesse;
    }
}
