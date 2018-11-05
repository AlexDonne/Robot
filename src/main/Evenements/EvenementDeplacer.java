package Evenements;

import Robots.AbstractRobot;

public class EvenementDeplacer extends Evenement {
    AbstractRobot robot;
    int i;
    int j;

    public EvenementDeplacer(long date, AbstractRobot robot, int i, int j) {
        super(date);
        this.robot = robot;
        this.i = i;
        this.j = j;
    }

    @Override
    public void execute() {
        System.out.println(this.getDate() + " : Déplacement de " + this.robot + " en case (" + this.i + ", " + this.j + ")");
    }
}
