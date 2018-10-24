package Utils;

import java.util.*;

/**
 * Sert à  associer les déplacements possibles pour chaque type.
 */
public class DeplacementsRobots {

    public static final Map<TypesRobot, List<NatureTerrain>> deplacements;

    static {
        Map<TypesRobot, List<NatureTerrain>> dep = new HashMap<>();
        List<NatureTerrain> depDrone = new ArrayList<>();
        depDrone.add(NatureTerrain.EAU);
        depDrone.add(NatureTerrain.FORET);
        depDrone.add(NatureTerrain.ROCHE);
        depDrone.add(NatureTerrain.HABITAT);
        depDrone.add(NatureTerrain.TERRAIN_LIBRE);
        dep.put(TypesRobot.DRONE, depDrone);

        List<NatureTerrain> depRoue = new ArrayList<>();
        depRoue.add(NatureTerrain.TERRAIN_LIBRE);
        depRoue.add(NatureTerrain.HABITAT);
        dep.put(TypesRobot.ROUES, depRoue);

        List<NatureTerrain> depPattes = new ArrayList<>();
        depPattes.add(NatureTerrain.EAU);
        dep.put(TypesRobot.PATTES, depPattes);

        List<NatureTerrain> depChenille = new ArrayList<>();
        depChenille.add(NatureTerrain.EAU);
        depChenille.add(NatureTerrain.ROCHE);
        dep.put(TypesRobot.CHENILLE, depChenille);

        deplacements = Collections.unmodifiableMap(dep);
    }
}
