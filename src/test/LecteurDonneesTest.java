package test;

import Environnement.NatureTerrain;
import Robots.StrategieDeplacement.StrategieDeplacementDijkstra;
import io.DonneesSimulation;
import io.LecteurDonnees;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitaire pour vérifier que les objets sont correctement construits
 */
public class LecteurDonneesTest {

    @Test
    public void testLire() throws Exception {
        DonneesSimulation donneesSimulation;
        donneesSimulation = LecteurDonnees.lire("cartes/carteSujet.map", new StrategieDeplacementDijkstra());
        Assert.assertEquals(6, donneesSimulation.getIncendies().size());
        Assert.assertEquals(3, donneesSimulation.getRobots().size());
        Assert.assertEquals(NatureTerrain.EAU, donneesSimulation.getCarte().getCase(5, 2).getNatureTerrain());
    }
}

