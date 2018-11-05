package Robots;

import Environnement.Case;

public class RobotAerien extends AbstractRobot{

    public RobotAerien(Case position){
        super(position);
        this.reservoir = 10000;
        this.vitesse = 100;
    }

    @Override
    public void setVitesse(int vitesse) {
        if (vitesse > 150) {
            this.vitesse = 150;
        }
        else {
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
}
