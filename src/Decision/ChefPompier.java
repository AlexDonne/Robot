package Decision;

import io.DonneesSimulation;
import io.Simulateur;

/**
 * Représente le chef qui va décider coment les différents robots vont prendre en charge les incendies
 */
public class ChefPompier {

    private DonneesSimulation donneesSimulation;

    /**
     * La stratégie adoptée
     */
    private IStrategieGlobale strategie;

    public ChefPompier(DonneesSimulation donneesSimulation, IStrategieGlobale strategie) {
        this.donneesSimulation = donneesSimulation;
        this.strategie = strategie;
    }

    public void prendreDecisions(Simulateur simulateur) {
        this.strategie.prendreDecisions(simulateur, this.donneesSimulation.getIncendies(), this.donneesSimulation.getRobots(), this.donneesSimulation.getCarte());
    }
}
