package io;

import Environnement.Graphe;
import Environnement.Incendie;
import Environnement.Itineraire;
import Evenements.Evenement;
import Evenements.EvenementDepartIncendie;
import Evenements.EvenementDeplacer;
import Evenements.EvenementFinIncendie;
import Robots.AbstractRobot;
import gui.*;
import gui.Rectangle;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;


public class Simulateur implements Simulable {

    private List<Evenement> listeEvenements;

    private GUISimulator guiSimulator;
    private long dateSimulation;

    private DonneesSimulation donneesSimulation;

    public Simulateur(long dateSimulation, String fichier) {
        this.dateSimulation = dateSimulation;
        this.listeEvenements = new LinkedList<>();
        try {
            this.donneesSimulation = LecteurDonnees.lire(fichier);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fichier + " not found");
        } catch (DataFormatException e) {
            System.out.println("Data not in good format");
        }
        this.guiSimulator = new GUISimulator(this.donneesSimulation.getCarte().getNbLignes() * 100, this.donneesSimulation.getCarte().getNbColonnes() * 100, Color.BLACK);
        guiSimulator.setSimulable(this);
        draw();
    }


    private void draw() {
        guiSimulator.reset();
        for (int i = 0; i < this.donneesSimulation.getCarte().getNbLignes(); i++) {
            for (int j = 0; j < this.donneesSimulation.getCarte().getNbColonnes(); j++) {
                Color color = this.donneesSimulation.getCarte().getCase(i, j).getNatureTerrain().getColor();
                guiSimulator.addGraphicalElement(new Rectangle(j * 100 + 50, i * 100 + 50, Color.white, color, 100));
            }
        }
        for(Incendie incendie : this.donneesSimulation.getIncendies()){
            guiSimulator.addGraphicalElement(new ImageElement(incendie.getPosition().getLigne() * 100 + 15, incendie.getPosition().getColonne() *100 + 15, "images/flammes.png", 80, 80, null));
        }

        for (AbstractRobot robot : this.donneesSimulation.getRobots()) {
            guiSimulator.addGraphicalElement(new Oval(robot.getPosition().getLigne() * 100 + 50, robot.getPosition().getColonne() * 100 + 50, robot.getType().getColor(), robot.getType().getColor() , 50));
        }

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
