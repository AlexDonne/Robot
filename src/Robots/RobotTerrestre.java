package Robots;

import Environnement.Case;

public abstract class RobotTerrestre extends AbstractRobot{
    protected RobotTerrestre(Case position){
        super(position);
    }

    @Override
    abstract public void remplirReservoir();
}
