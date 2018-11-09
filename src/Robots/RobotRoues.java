package Robots;

import Environnement.Case;
import Robots.StrategieDeplacement.IStrategieDeplacement;

public class RobotRoues extends AbstractRobot {

    public RobotRoues(Case position, IStrategieDeplacement strategieDeplacement) {
        super(position, strategieDeplacement);
        this.reservoir = 5000;
        this.vitesse = 80000;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 5000;
    }

    @Override
    public TypesRobot getType() {
        return TypesRobot.ROUES;
    }

    @Override
    double getTempsOperation(int vol) {
        return vol * 0.05;
    }
}
