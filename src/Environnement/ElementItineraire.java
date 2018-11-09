package Environnement;

/**
 * Classe qui représente un élément d'un itinéraire, qui associe une case à un temps
 */
public class ElementItineraire {

    /**
     * Temps qu'il faut pour aller à la case
     */
    private double temps;

    private Case position;

    public ElementItineraire(Case position, double temps) {
        this.temps = temps;
        this.position = position;
    }

    public Case getPosition() {
        return this.position;
    }

    public double getTemps() {
        return this.temps;
    }

    @Override
    public String toString() {
        return "(" + this.position.getLigne() + "," + this.position.getColonne() + "," + (double) (Math.round(this.temps * 100)) / 100 + ")";
    }
}
