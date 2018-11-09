package Robots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumeration des types de robot, avec pour chaque type ses déplacements possibles, son image sur l'interface et la vitesse de rechargement
 */
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
            "images/drone.png",
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
            "images/pattes.png",
            -1
    ),
    ROUES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            "images/roues.png",
            10
    ),
    CHENILLES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    ),
            "images/chenilles.png",
            5
    );

    /**
     * Liste des environnements sur lequel le type de robot peut aller
     */
    private List<Environnement.NatureTerrain> deplacements;

    /**
     * Lien vers l'image pour l'interface
     */
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
