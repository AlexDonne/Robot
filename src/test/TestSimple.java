import Environnement.*;
import Robots.AbstractRobot;
import Robots.EquipeRobots;
import Robots.RobotChenille;

import java.util.ArrayList;
import java.util.List;

public class TestSimple {
    public static void main(String[] args) throws Exception {
        // Constitution de la Carte
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

        // Constitution de l'équipe de robots
        AbstractRobot r1 = new RobotChenille(carte.getCase(0, 5));
        AbstractRobot r2 = new RobotChenille(carte.getCase(3, 7));
        List<AbstractRobot> listeRobots = new ArrayList<>();
        listeRobots.add(r1);
        listeRobots.add(r2);
        EquipeRobots equipe = new EquipeRobots(listeRobots);

        // Constitution de l'ensemble d'listeIncendies
        Incendie i1 = new Incendie(carte.getCase(5, 3), 30);
        Incendie i2 = new Incendie(carte.getCase(0, 0), 40);
        Incendie i3 = new Incendie(carte.getCase(2, 1), 50);
        List<Incendie> listeIncendies = new ArrayList<Incendie>();
        listeIncendies.add(i1);
        listeIncendies.add(i2);
        listeIncendies.add(i3);
        EnsembleIncendies ensemble = new EnsembleIncendies(listeIncendies);


        // Constitution des graphes pour chaque type de robots
        Graphe graphe = carte.toGraphe(r1); // robot chenille
        Simulateur simulateur = new Simulateur(-9);

        long t = 0;
        while (!ensemble.tousPrisEnCharge()) {
            if (!equipe.tousOccupes(t)) {
                AbstractRobot robot = equipe.robotLibre(t);
                Incendie incendie = ensemble.incendieLibre();
                simulateur.ajouteDepartPourFeu(graphe, robot, incendie, t);
            } else {
                t++;
            }
        }
        simulateur.demarrerSimulation();
    }
}
