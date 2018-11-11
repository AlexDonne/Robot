package Decision;

import io.DonneesSimulation;
import io.Simulateur;

/**
 * Représente le chef qui va décider coment les différents robots vont prendre en charge les incendies
 */
public class ChefPompier {

    /**
     * Les données de la simulation, le chef connaît la position des incendies, des robots et il connaît la carte
     */
    private DonneesSimulation donneesSimulation;

    /**
     * La stratégie adoptée
     */
    private IStrategieGlobale strategie;

    public ChefPompier(DonneesSimulation donneesSimulation, IStrategieGlobale strategie) {
        this.donneesSimulation = donneesSimulation;
        this.strategie = strategie;
    }

    /**
     * Délègue la prise de décision à la stratégie
     * @param simulateur
     */
    public void prendreDecisions(Simulateur simulateur) {
        this.strategie.prendreDecisions(simulateur, this.donneesSimulation.getIncendies(), this.donneesSimulation.getRobots(), this.donneesSimulation.getCarte());
    }
}
