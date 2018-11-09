package Evenements;

import Environnement.Incendie;
import Robots.AbstractRobot;

/**
 * Evénement qui représente l'action de déverser de l'eau sur un incendie
 */
public class EvenementDeverserEauSurIncendie extends Evenement {

    private Incendie incendie;

    /**
     * Le robot qui est chargé de l'incendie
     */
    private AbstractRobot robot;

    /**
     * Volume d'eau qui va être déversé sur l'incendie
     */
    private int volume;

    public EvenementDeverserEauSurIncendie(long date, Incendie incendie, AbstractRobot robot, int volume) {
        super(date);
        this.incendie = incendie;
        this.robot = robot;
        this.volume = volume;
    }

    /**
     * Deverse le maximum d'eau que le robot peut verser sur l'incendie, puis libère le robot
     */
    @Override
    public void execute() {
        if ((incendie.getEauNecessaire() <= robot.getReservoir()) || robot.getReservoir() == -1) {
            incendie.setEauNecessaire(0);
        } else {
            incendie.setEauNecessaire(incendie.getEauNecessaire() - robot.getReservoir());
            incendie.setPrisEnCharge(false);
        }
        this.robot.deverserEauSurIncendie(this.volume);
        this.robot.setOccupe(false);
        System.out.println(this.volume + " déversé sur incendie en case " + incendie.getPosition().getLigne() + "-" + incendie.getPosition().getColonne());
        System.out.println("Reste à déverser " + incendie.getEauNecessaire());
    }
}
