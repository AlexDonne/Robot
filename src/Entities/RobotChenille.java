package Entities;

public class RobotChenille extends RobotTerrestre{
    public RobotChenille(Case position){
        super(position);
        this.reservoir = 2000;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 2000;
    }
}
