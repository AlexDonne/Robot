import io.Simulateur;

public class Programm {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(9, "cartes/desertOfDeath-20x20.map");
        simulateur.start();
    }
}
