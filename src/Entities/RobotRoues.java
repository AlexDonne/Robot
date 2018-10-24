package Entities;

public class RobotRoues extends RobotTerrestre{
    public RobotRoues(Case position){
        super(position);
        this.reservoir = 5000;
    }

    @Override
    public void remplirReservoir() {
        this.reservoir = 5000;
    }
}
