package Robots.StrategieDeplacement;

import Environnement.Carte;
import Environnement.Case;
import Robots.AbstractRobot;

import java.util.Comparator;

/**
 * Comparateur pour trier la liste de robots en fonction de leur distance à l'incendie
 */
public class TempsDeplacementComparator implements Comparator<AbstractRobot> {

    private Case arrivee;

    private Carte carte;

    public TempsDeplacementComparator(Case arrivee, Carte carte) {
        this.arrivee = arrivee;
        this.carte = carte;
    }

    /**
     * Tous les -1 sont placés à la fin (retourne -1 si robot ne peut pas y aller)
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(AbstractRobot o1, AbstractRobot o2) {
        if (o1.tempsDeplacement(this.arrivee, this.carte) == -1) {
            return 1;
        } else if (o2.tempsDeplacement(this.arrivee, this.carte) == -1) {
            return -1;
        } else if (o1.tempsDeplacement(this.arrivee, this.carte) < o2.tempsDeplacement(this.arrivee, this.carte)) {
            return -1;
        } else {
            return 1;
        }
    }
}
