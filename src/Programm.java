import Decision.StrategieSimple;
import io.Simulateur;

/**
 * Programme principal (utiliser Makefile)
 */
public class Programm {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(0, args[0], new StrategieSimple());
        simulateur.start();
    }
}
