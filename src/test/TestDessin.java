import gui.GUISimulator;
import io.Simulateur;

import java.awt.*;

public class TestDessin {
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        Simulateur simulateur = new Simulateur(9, gui);
    }
}
