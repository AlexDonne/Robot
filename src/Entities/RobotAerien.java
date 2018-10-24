package Entities;

import Utils.NatureTerrain;

import java.util.ArrayList;

public class RobotAerien extends AbstractRobot{

    public RobotAerien(Case position){
        super(position);
        this.reservoir = 10000;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 10000;
    }
}
