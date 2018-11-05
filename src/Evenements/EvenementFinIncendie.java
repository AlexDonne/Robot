package Evenements;

import Environnement.Incendie;

public class EvenementFinIncendie extends Evenement {
    Incendie incendie;

    public EvenementFinIncendie(long date, Incendie incendie) {
        super(date);
        this.incendie = incendie;
    }

    @Override
    public void execute() {
        System.out.println(
                this.getDate() +
                        " : L'opération est une réussite ! Fin de l'incendie en case ("
                        + this.incendie.getPosition().getLigne()
                        + ", " + this.incendie.getPosition().getColonne() + ")"
        );
    }
}
