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

    /**
     * Eteint incendie, si reservoir vide au début, va se recharger et l'incendie sera pris en charge par un autre
     *
     * @param carte
     * @param incendie
     * @param simulateur
     */
    public boolean seChargeDeLincendie(Carte carte, Incendie incendie, Simulateur simulateur) {
        Graphe graphe = carte.toGraphe(this);
        if (this.reservoir == 0) {
            this.seRecharger(carte, simulateur, graphe);
            return false;
        }
        Itineraire itineraire;


        try {
            itineraire = graphe.dijkstra(this.position, incendie.getPosition(), carte);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        this.setOccupe(true);
        this.creerDeplacements(itineraire, simulateur);
        System.out.println("Voici l'itinéraire inversé: " + itineraire);
        incendie.prendreEnCharge();
        double temps = itineraire.getMapItineraire().isEmpty() ? 0 : itineraire.getMapItineraire().get(0).getTemps();
        simulateur.ajouteEvenement(new EvenementEteindreIncendie(Math.round(temps + simulateur.getDateSimulation()), incendie, this));

        return true;
    }

    private void creerDeplacements(Itineraire itineraire, Simulateur simulateur) {
        for (ElementItineraire elementItineraire : itineraire.getMapItineraire()) {
            EvenementDeplacer e = new EvenementDeplacer(Math.round(elementItineraire.getTemps() + simulateur.getDateSimulation()), this, elementItineraire.getPosition());
            simulateur.ajouteEvenement(e);
        }
    }

    private void seRecharger(Carte carte, Simulateur simulateur, Graphe graphe) {
        long t = simulateur.getDateSimulation();
        Itineraire iti;
        try {
            iti = graphe.dijkstraEau(this.position, carte, this);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        this.creerDeplacements(iti, simulateur);

        int taille = iti.getMapItineraire().size();

        this.setOccupe(true); // le robot n'est plus libre;
        if (taille == 0) { // le robot est déjà sur place
            EvenementFinRechargementRobot e = new EvenementFinRechargementRobot(t + this.getType().getTempsRechargement(), this); // tous les robots mettent 3 pour se remplir (le niveau d'eau du robot est actualisé lors de l'éxécution de cet évènement ainsi que sa liberté)
            simulateur.ajouteEvenement(e);
        } else {
            EvenementFinRechargementRobot e = new EvenementFinRechargementRobot(this.getType().getTempsRechargement() + t + Math.round(iti.getMapItineraire().get(0).getTemps()), this); // tous les robots mettent 3 pour se remplir (le niveau d'eau du robot est actualisé los de l'éxécution de cet évènement)
            simulateur.ajouteEvenement(e);
        }
    }
}
