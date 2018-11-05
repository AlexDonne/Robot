package io;

import Environnement.Graphe;
import Environnement.Incendie;
import Environnement.Itineraire;
import Evenements.Evenement;
import Evenements.EvenementDepartIncendie;
import Evenements.EvenementDeplacer;
import Evenements.EvenementFinIncendie;
import Robots.AbstractRobot;
import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Simulateur implements Simulable {

    List<Evenement> listeEvenements;

    GUISimulator guiSimulator;
    long dateSimulation;

    public Simulateur(long dateSimulation, GUISimulator gui) {
        this.guiSimulator = gui;
        gui.setSimulable(this);
        this.dateSimulation = dateSimulation;
        this.listeEvenements = new LinkedList<>();
    }

    @Override
    public void next() {

    }

    @Override
    public void restart() {

    }

    public void incrementeDate(int t) {
        this.dateSimulation += t;
    }

    public void ajouteEvenement(Evenement e) {
        int i = 0;
        while (i < this.listeEvenements.size() && e.getDate() > this.listeEvenements.get(i).getDate()) {
            i++;
        }
        this.listeEvenements.add(i, e);
    }

    public void ajouteItineraire(Itineraire iti, AbstractRobot r, long t) { // t est le décalage
        for (int i = 0; i < iti.getListeSommets().size(); i++) {
            EvenementDeplacer e = new EvenementDeplacer(Math.round(iti.getListeDurees().get(i)) + t, r, iti.getListeSommets().get(i) / 8, iti.getListeSommets().get(i) % 8);
            this.ajouteEvenement(e);
        }
    }

    public void ajouteDepartPourFeu(Graphe graphe, AbstractRobot robot, Incendie incendie, long t) {
        int i = incendie.getPosition().getLigne();
        int j = incendie.getPosition().getColonne();
        int x = robot.getPosition().getLigne();
        int y = robot.getPosition().getColonne();
        Itineraire iti = graphe.dijkstra(x * 8 + y, i * 8 + j);
        this.ajouteItineraire(iti, robot, t);

        EvenementDepartIncendie e1 = new EvenementDepartIncendie(t, incendie, robot);
        this.ajouteEvenement(e1);

        int taille = iti.getListeSommets().size();
        robot.setDateFinOccupation(1 + t + Math.round(iti.getListeDurees().get(taille - 1))); // prend 1 min d'eteindre l'incendie
        // on actualise l'incendie
        incendie.prendreEnCharge();
        incendie.setDateFin(t + Math.round(iti.getListeDurees().get(taille - 1)));

        EvenementFinIncendie e2 = new EvenementFinIncendie(1 + t + Math.round(iti.getListeDurees().get(taille - 1)), incendie);
        this.ajouteEvenement(e2);
    }

    public void demarrerSimulation() {
        for (Evenement e : this.listeEvenements) {
            e.execute();
        }
    }
}
