package Environnement;

import java.awt.*;

/**
 * Enum pour les différentes natures de terrain, qui associe la couleur pour l'interface graphique
 */
public enum NatureTerrain {
    EAU(Color.BLUE),
    FORET(Color.GREEN),
    ROCHE(Color.lightGray),
    TERRAIN_LIBRE(Color.ORANGE),
    HABITAT(Color.yellow);

    private Color color;

    NatureTerrain(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
