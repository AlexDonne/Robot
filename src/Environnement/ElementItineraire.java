package Environnement;


public class ElementItineraire {

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
        String message = "(" + this.position.getLigne() + "," + this.position.getColonne() + "," + (double)(Math.round(this.temps*100)) / 100 + ")";
        return message;
    }
}
