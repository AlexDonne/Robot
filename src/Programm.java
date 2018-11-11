import Decision.StrategieGlobaleOptimisee;
import Decision.StrategieGlobaleSimple;
import Robots.StrategieDeplacement.StrategieDeplacementDijkstra;
import io.Simulateur;

/**
 * Programme principal (utiliser Makefile), ici sont choisies les différentes stratégies
 */
public class Programm {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(0, args[0], new StrategieGlobaleOptimisee(), new StrategieDeplacementDijkstra());
        simulateur.start();
    }
}
