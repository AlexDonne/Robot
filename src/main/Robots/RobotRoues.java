package Robots;

import Environnement.Case;

public class RobotRoues extends RobotTerrestre{
    public RobotRoues(Case position){
        super(position);
        this.reservoir = 5000;
        this.vitesse = 80;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 5000;
    }

    @Override
    public TypesRobot getType() {
        return TypesRobot.ROUES;
    }
}
