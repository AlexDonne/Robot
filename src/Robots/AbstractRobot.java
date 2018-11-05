package Robots;

import Environnement.Case;
import Environnement.NatureTerrain;

public abstract class AbstractRobot {

    private long dateFinOccupation = 0;

    protected Case position;

    protected int reservoir;

    protected int vitesse;

    protected AbstractRobot(Case position){
        this.position = position;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    abstract public void remplirReservoir();

    public void deverserEau(int vol){
        this.reservoir -= vol;
    }

    public int getVitesse(NatureTerrain natureTerrain){
        return this.vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setDateFinOccupation(long t) {
        this.dateFinOccupation = t;
    }

    public long getDateFinOccupation() {
        return this.dateFinOccupation;
    }

    public abstract TypesRobot getType();

}
