import Map.Case;
import Utils.NatureTerrain;

public class AbstractRobot {

    private Case position;

    private int reservoir;

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public void remplirReservoir() {
    }

    public void deverserEau(int vol){
        this.reservoir -= vol;
    }

    public void getVitesse(NatureTerrain natureTerrain){

    }
}
