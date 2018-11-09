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
            new String("images/drone.png"),
            30
    ),
    PATTES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.ROCHE,
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            new String("images/pattes.png"),
            -1
    ),
    ROUES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            new String("images/roues.png"),
            10
    ),
    CHENILLES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            new String("images/chenilles.png"),
            5
    );

    private List<Environnement.NatureTerrain> deplacements;

    private String url;

    /**
     * Temps pour se recharger, en minutes
     */
    private int tempsRechargement;

    TypesRobot(List<Environnement.NatureTerrain> deplacements, String url, int tempsRechargement) {
        this.deplacements = deplacements;
        this.url = url;
        this.tempsRechargement = tempsRechargement;
    }

    public List<Environnement.NatureTerrain> getDeplacements() {
        return deplacements;
    }

    public String getUrl() {
        return url;
    }

    public int getTempsRechargement() {
        return tempsRechargement;
    }
}
