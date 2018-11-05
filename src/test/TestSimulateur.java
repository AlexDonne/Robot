import Environnement.*;
import Robots.AbstractRobot;
import Robots.RobotChenille;
import io.Simulateur;

public class TestSimulateur {
    public static void main(String[] args) {
        // RobotChenille r1 = new RobotChenille(0, 0, "Ivana");
        // EvenementDeplacer e1 = new EvenementDeplacer(1, r1, 1, 0);
        // EvenementDeplacer e2 = new EvenementDeplacer(2, r1, 2, 0);
        // EvenementDeplacer e3 = new EvenementDeplacer(3, r1, 2, 1);
        // Simulateur simulateur = new Simulateur(-9);
        // simulateur.ajouteEvenement(e1);
        // simulateur.ajouteEvenement(e2);
        // simulateur.ajouteEvenement(e3);
        // System.out.println(r1.getX() + ";" + r1.getY());
        // simulateur.demarrerSimulation();
        // System.out.println(r1.getX() + ";" + r1.getY());

        Carte carte = new Carte(8, 8, 480.0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                carte.ajouterCase(i, j, new Case(NatureTerrain.TERRAIN_LIBRE));
            }
        }
        carte.ajouterCase(1, 4, new Case(NatureTerrain.ROCHE));
        carte.ajouterCase(1, 6, new Case(NatureTerrain.ROCHE));

        carte.ajouterCase(2, 2, new Case(NatureTerrain.EAU));
        carte.ajouterCase(2, 3, new Case(NatureTerrain.EAU));
        carte.ajouterCase(3, 2, new Case(NatureTerrain.EAU));
        carte.ajouterCase(3, 3, new Case(NatureTerrain.EAU));
        carte.ajouterCase(4, 2, new Case(NatureTerrain.EAU));
        carte.ajouterCase(5, 2, new Case(NatureTerrain.EAU));
        carte.ajouterCase(6, 2, new Case(NatureTerrain.EAU));
        carte.ajouterCase(6, 3, new Case(NatureTerrain.EAU));
        carte.ajouterCase(7, 3, new Case(NatureTerrain.EAU));

        carte.ajouterCase(2, 5, new Case(NatureTerrain.FORET));
        carte.ajouterCase(3, 5, new Case(NatureTerrain.FORET));
        carte.ajouterCase(3, 6, new Case(NatureTerrain.FORET));
        carte.ajouterCase(4, 5, new Case(NatureTerrain.FORET));
        carte.ajouterCase(4, 6, new Case(NatureTerrain.FORET));

        carte.ajouterCase(5, 5, new Case(NatureTerrain.HABITAT));
        carte.ajouterCase(5, 6, new Case(NatureTerrain.HABITAT));
        carte.ajouterCase(7, 1, new Case(NatureTerrain.HABITAT));
        carte.ajouterCase(7, 2, new Case(NatureTerrain.HABITAT));

        carte.afficher();

        int x = 2;
        int y = 1;
        AbstractRobot r1 = new RobotChenille(carte.getCase(x, y));
        AbstractRobot r2 = new RobotChenille(carte.getCase(x, y));
        Graphe graphe = carte.toGraphe(r1);
        Graphe graphe2 = carte.toGraphe(r2);
        Simulateur simulateur = new Simulateur(-9);
        // Itineraire iti = graphe.dijkstra(x*8 + y, 43);
        // Itineraire iti2 = graphe2.dijkstra(x*8 + y, 43);
        //

        //
        // simulateur.ajouteItineraire(iti, r1, 30);
        // simulateur.ajouteItineraire(iti2, r2, 0);
        // System.out.println(r1.getX() + ";" + r1.getY());
        // System.out.println(r2.getX() + ";" + r2.getY());
        // simulateur.demarrerSimulation();
        // System.out.println(r1.getX() + ";" + r1.getY());
        // System.out.println(r2.getX() + ";" + r2.getY());
        System.out.println(r1.getPosition().getLigne() + ";" + r1.getPosition().getColonne());
        Incendie i1 = new Incendie(carte.getCase(5, 3), 40);
        simulateur.ajouteDepartPourFeu(graphe, r1, i1, 30);
        simulateur.demarrerSimulation();
        System.out.println(r1.getPosition().getLigne() + ";" + r1.getPosition().getColonne());

    }
}
