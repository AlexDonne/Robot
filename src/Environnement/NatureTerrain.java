package Environnement;

import java.awt.*;

public enum NatureTerrain {
    EAU(Color.BLUE),
    FORET(Color.GREEN),
    ROCHE(Color.DARK_GRAY),
    TERRAIN_LIBRE(Color.ORANGE),
    HABITAT(Color.BLACK);

    private Color color;

    NatureTerrain(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
