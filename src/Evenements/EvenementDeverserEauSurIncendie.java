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

    public EvenementDeverserEauSurIncendie(long date, Incendie incendie, AbstractRobot robot) {
        super(date);
        this.incendie = incendie;
        this.robot = robot;
    }

    /**
     * Deverse le maximum d'eau que le robot peut verser sur l'incendie, puis libère le robot
     */
    @Override
    public void execute() {
        int vol;
        if ((incendie.getEauNecessaire() <= robot.getReservoir()) || robot.getReservoir() == -1) {
            vol = incendie.getEauNecessaire();
            incendie.setEauNecessaire(0);
        } else {
            vol = robot.getReservoir();
            incendie.setEauNecessaire(incendie.getEauNecessaire() - robot.getReservoir());
            incendie.setPrisEnCharge(false);
        }
        double temps = this.robot.deverserEauSurIncendie(vol);
        this.date += temps;
        this.robot.setOccupe(false);
        System.out.println(vol + " déversé sur incendie en case " + incendie.getPosition().getLigne() + "-" + incendie.getPosition().getColonne());
        System.out.println("Reste à déverser " + incendie.getEauNecessaire());
    }
}
