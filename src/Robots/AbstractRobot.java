package Robots;

import Environnement.*;
import Evenements.EvenementDeplacer;
import Evenements.EvenementEteindreIncendie;
import Evenements.EvenementFinRechargementRobot;
import io.Simulateur;

import java.util.Iterator;
import java.util.Map;

public abstract class AbstractRobot {

    protected Case position;

    protected int reservoir;

    protected int vitesse;

    protected boolean occupe = false;

    protected AbstractRobot(Case position) {
        this.position = position;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    abstract public void remplirReservoir();

    public void deverserEauSurIncendie(int vol) {
        this.reservoir -= vol;
        this.occupe = false;
    }

    public int getVitesse(NatureTerrain natureTerrain) {
        return this.vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getReservoir() {
        return reservoir;
    }

    public abstract TypesRobot getType();

    public boolean isOccupe() {
        return occupe;
    }

    public void setOccupe(boolean occupe) {
        this.occupe = occupe;
    }

    public boolean peutAller(Carte carte, Case positionFuture) {
        Graphe graphe = carte.toGraphe(this);
        try {
            graphe.dijkstra(this.position, positionFuture, carte);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Eteint incendie, si reservoir vide au début, va se recharger et l'incendie sera pris en charge par un autre
     *
     * @param carte
     * @param incendie
     * @param simulateur
     */
    public void seChargerDeLincendie(Carte carte, Incendie incendie, Simulateur simulateur) {
        Graphe graphe = carte.toGraphe(this);
        if (this.reservoir == 0) {
            this.seRecharger(carte, simulateur, graphe);
            return;
        }
        Itineraire itineraire;

        try {
            itineraire = graphe.dijkstra(this.position, incendie.getPosition(), carte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        this.creerDeplacements(carte, itineraire, simulateur);
        incendie.prendreEnCharge();

        simulateur.ajouteEvenement(new EvenementEteindreIncendie(Math.round(itineraire.getMapItineraire().get(incendie.getPosition()) + simulateur.getDateSimulation()), incendie, this));
    }

    private void creerDeplacements(Carte carte, Itineraire itineraire, Simulateur simulateur) {
        this.occupe = true;
        for (Map.Entry<Case, Double> entry : itineraire.getMapItineraire().entrySet()) {
            EvenementDeplacer e = new EvenementDeplacer(Math.round(entry.getValue() + simulateur.getDateSimulation()), this, entry.getKey());
            simulateur.ajouteEvenement(e);
        }
    }

    private void seRecharger(Carte carte, Simulateur simulateur, Graphe graphe) {
        long t = simulateur.getDateSimulation();
        Itineraire iti;
        try {
            iti = graphe.dijkstraEau(this.position, carte, this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        this.creerDeplacements(carte, iti, simulateur);

        int taille = iti.getMapItineraire().size();

        this.setOccupe(true); // le robot n'est plus libre;
        if (taille == 0) { // le robot est déjà sur place
            EvenementFinRechargementRobot e = new EvenementFinRechargementRobot(t + 3, this); // tous les robots mettent 3 pour se remplir (le niveau d'eau du robot est actualisé lors de l'éxécution de cet évènement ainsi que sa liberté)
            simulateur.ajouteEvenement(e);
        } else {
            EvenementFinRechargementRobot e = new EvenementFinRechargementRobot(3 + t + Math.round((Double) iti.getMapItineraire().values().toArray()[taille - 1]), this); // tous les robots mettent 3 pour se remplir (le niveau d'eau du robot est actualisé los de l'éxécution de cet évènement)
            simulateur.ajouteEvenement(e);
        }
    }
}
