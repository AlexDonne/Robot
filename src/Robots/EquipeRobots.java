package Robots;

import java.util.List;

public class EquipeRobots {
    private List<AbstractRobot> listeRobots;

    public EquipeRobots(List<AbstractRobot> listeRobots) {
        this.listeRobots = listeRobots;
    }

    public boolean tousOccupes(long t) {
        for (AbstractRobot r : this.listeRobots) {
            if (r.getDateFinOccupation() <= t) {
                return false;
            }
        }
        return true;
    }

    // retourne un robot libre (n'importe lequel)
    public AbstractRobot robotLibre(long t) throws Exception {
        for (AbstractRobot r : this.listeRobots) {
            if (r.getDateFinOccupation() <= t) {
                return r;
            }
        }
        throw new Exception("Cas pas censé arriver");
    }
}
