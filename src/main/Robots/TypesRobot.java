package Robots;

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
    )
    ),
    PATTES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.ROCHE,
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    )
    ),
    ROUES(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    )
    ),
    CHENILLE(new ArrayList<Environnement.NatureTerrain>(
            Arrays.asList(
                    Environnement.NatureTerrain.HABITAT,
                    Environnement.NatureTerrain.FORET,
                    Environnement.NatureTerrain.TERRAIN_LIBRE
            )
    )
    );

    private List<Environnement.NatureTerrain> deplacements;

    TypesRobot(List<Environnement.NatureTerrain> deplacements) {
        this.deplacements = deplacements;
    }

    public List<Environnement.NatureTerrain> getDeplacements() {
        return deplacements;
    }
}
