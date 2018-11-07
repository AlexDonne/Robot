package Evenements;

import Environnement.Incendie;
import Robots.AbstractRobot;

public class EvenementEteindreIncendie extends Evenement {

    private Incendie incendie;
    private AbstractRobot robot;

    public EvenementEteindreIncendie(long date, Incendie incendie, AbstractRobot robot) {
        super(date);
        this.incendie = incendie;
        this.robot = robot;
    }

    @Override
    public void execute() {
        int vol;
        if ((incendie.getEauNecessaire() <= robot.getReservoir()) || robot.getReservoir() == -1) {
            vol = incendie.getEauNecessaire();
            incendie.setEauNecessaire(0);
        } else {
            vol = robot.getReservoir();
            incendie.setEauNecessaire(incendie.getEauNecessaire() - robot.getReservoir());
            incendie.plusPrisEnCharge();

        }
        this.robot.deverserEauSurIncendie(vol);
        System.out.println(vol + " déversé sur incendie en case " + incendie.getPosition().getLigne() + "-" + incendie.getPosition().getColonne());
        System.out.println("Reste à déverser " + incendie.getEauNecessaire());
    }
}
