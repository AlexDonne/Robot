package Entities;

import Entities.Case;

public class Incendie {
    private Case position;

    private int quantEau;

    public void decrementQuantEau (){
        quantEau --;
    }

    public Incendie(Case position, int quantEau){
        this.position = position;
        this.quantEau = quantEau;
    }
}
