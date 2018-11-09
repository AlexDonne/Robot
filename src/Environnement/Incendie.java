package Environnement;

public class Incendie {

    private Case position;

    /**
     * True si un robot s'occupe de l'incendie
     */
    private boolean prisEnCharge = false;

    /**
     * quantité d'eau nécessaire pour éteindre l'incendie
     */
    private int eauNecessaire;

    public Incendie(Case position, int eauNecessaire) {
        this.position = position;
        this.eauNecessaire = eauNecessaire;
    }

    public void setPrisEnCharge(boolean prisEnCharge) {
        this.prisEnCharge = prisEnCharge;
    }

    public boolean estPrisEnCharge() {
        return this.prisEnCharge;
    }

    public Case getPosition() {
        return position;
    }

    public int getEauNecessaire() {
        return this.eauNecessaire;
    }

    public void setEauNecessaire(int eauNecessaire) {
        this.eauNecessaire = eauNecessaire;
    }
}
