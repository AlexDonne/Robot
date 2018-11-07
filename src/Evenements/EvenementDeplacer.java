package Evenements;

import Environnement.Case;
import Robots.AbstractRobot;
import Robots.TypesRobot;

public class EvenementDeplacer extends Evenement {
    private AbstractRobot robot;
    private Case arrivee;

    public EvenementDeplacer(long date, AbstractRobot robot, Case arrivee) {
        super(date);
        this.robot = robot;
        this.arrivee = arrivee;
    }

    @Override
    public void execute() {


        System.out.printf("Déplacement de %d-%d en %d-%d \n", robot.getPosition().getLigne(), robot.getPosition().getColonne(), arrivee.getLigne(), arrivee.getColonne());

        this.robot.setPosition(this.arrivee);
    }
}
