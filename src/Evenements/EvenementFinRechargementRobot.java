package Evenements;

import Robots.AbstractRobot;

/**
 * Evénement qui représente le rechargement d'un robot
 */
public class EvenementFinRechargementRobot extends Evenement {

    private AbstractRobot robot;

    public EvenementFinRechargementRobot(long date, AbstractRobot robot) {
        super(date);
        this.robot = robot;
    }

    /**
     * Remplit réservoir du robot et le libère
     */
    @Override
    public void execute() {
        this.robot.remplirReservoir();
        this.robot.setOccupe(false);
        System.out.println("Rechargement en case " + this.robot.getPosition().getLigne() + "-" + this.robot.getPosition().getColonne());
    }
}
