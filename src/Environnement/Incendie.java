package Environnement;

public class Incendie {

    private Case position;
    private long dateFin = -1; //pas encore eteint, on ne connait pas la date;
    private boolean prisEnCharge = false;
    private int eauNecessaire;

    public Incendie(Case position, int eauNecessaire) {
        this.position = position;
        this.eauNecessaire = eauNecessaire;
    }

    public boolean estPrisEnCharge() {
        return this.prisEnCharge;
    }

    public void prendreEnCharge() {
        this.prisEnCharge = true;
    }

    public void plusPrisEnCharge(){
        this.prisEnCharge = false;
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
