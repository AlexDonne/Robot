import Decision.StrategieGlobaleSimple;
import Robots.StrategieDeplacement.StrategieDeplacementDijkstra;
import io.Simulateur;

/**
 * Programme principal (utiliser Makefile)
 */
public class Programm {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(0, args[0], new StrategieGlobaleSimple(), new StrategieDeplacementDijkstra());
        simulateur.start();
    }
}
