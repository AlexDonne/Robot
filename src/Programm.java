import io.Simulateur;

/**
 * Programme principal (utiliser Makefile)
 */
public class Programm {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(0, "cartes/carteSujet.map");
        simulateur.start();
    }
}
