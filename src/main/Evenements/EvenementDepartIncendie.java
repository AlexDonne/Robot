package Evenements;

import Environnement.Incendie;
import Robots.AbstractRobot;
import Robots.RobotChenille;

public class EvenementDepartIncendie extends Evenement {
    Incendie incendie;
    AbstractRobot robot;

    public EvenementDepartIncendie(long date, Incendie incendie, AbstractRobot robot) {
        super(date);
        this.incendie = incendie;
        this.robot = robot;
    }

    @Override
    public void execute() {
        System.out.println(this.getDate() + " : Départ du robot " + robot + " pour l'incendie en case (" + this.incendie.getPosition().getLigne() + ", " + this.incendie.getPosition().getColonne() + ")");
    }
}
