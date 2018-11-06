package Robots;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

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
            new String("images/drone.png")
    ),
    PATTES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.ROCHE,
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            new String("images/pattes.png")
    ),
    ROUES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            new String("images/roues.png")
    ),
    CHENILLES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            new String("images/chenilles.png")
    );

    private List<Environnement.NatureTerrain> deplacements;
    private String url;
    TypesRobot(List<Environnement.NatureTerrain> deplacements, String url) {
        this.deplacements = deplacements;
        this.url = url;
    }

    public List<Environnement.NatureTerrain> getDeplacements() {
        return deplacements;
    }

    public String getUrl() {
        return url;
    }
}
