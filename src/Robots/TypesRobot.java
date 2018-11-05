package Robots;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TypesRobot {
    DRONE(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.EAU,
                    Environnement.NatureTerrain.ROCHE,
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            Color.decode("#cc33ff")
    ),
    PATTES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.ROCHE,
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            Color.decode("#cc33ff")
    ),
    ROUES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            Color.decode("#cc33ff")
    ),
    CHENILLE(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            Color.decode("#cc33ff")
    );

    private List<Environnement.NatureTerrain> deplacements;
    private Color color;
    TypesRobot(List<Environnement.NatureTerrain> deplacements, Color color) {
        this.deplacements = deplacements;
        this.color = color;
    }

    public List<Environnement.NatureTerrain> getDeplacements() {
        return deplacements;
    }

    public Color getColor() {
        return color;
    }
}
