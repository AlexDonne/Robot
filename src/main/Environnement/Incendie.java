package Environnement;

public class Incendie {

    private Case position;
    private long dateFin = -1; //pas encore eteint, on ne connait pas la date;
    private boolean prisEnCharge = false;
    private int quantEau;

    public void decrementQuantEau() {
        quantEau--;
    }

    public Incendie(Case position, int quantEau) {
        this.position = position;
        this.quantEau = quantEau;
    }

    public boolean estEteint(long t) {
        return this.dateFin >= 0 && this.dateFin <= t;
    }

    public void setDateFin(long t) {
        this.dateFin = t;
    }

    public boolean estPrisEnCharge() {
        return this.prisEnCharge;
    }

    public void prendreEnCharge() {
        this.prisEnCharge = true;
    }

    public Case getPosition() {
        return position;
    }
}
