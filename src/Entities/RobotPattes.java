package Entities;

public class RobotPattes extends RobotTerrestre{
    public RobotPattes(Case position){
        super(position);
        this.reservoir = -1; //Infini
    }

    @Override
    public void remplirReservoir() {

    }
}
