package io;

import Environnement.Incendie;
import Evenements.Evenement;
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

    private String fichier;

    public Simulateur(long dateSimulation, String fichier) {
        this.fichier = fichier;
        this.dateSimulation = dateSimulation;
        this.listeEvenements = new LinkedList<>();
    }

    public void start() {
        try {
            this.donneesSimulation = LecteurDonnees.lire(fichier);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fichier + " not found");
        } catch (DataFormatException e) {
            System.out.println("Data not in good format");
        }
        this.guiSimulator = new GUISimulator(this.donneesSimulation.getCarte().getNbLignes() * 100, this.donneesSimulation.getCarte().getNbColonnes() * 100, Color.BLACK);
        guiSimulator.setSimulable(this);
        prendreDecisions();
        draw();
    }

    private void prendreDecisions() {
        for (Incendie incendie : this.donneesSimulation.getIncendies()) {
            if (incendie.estPrisEnCharge()) {
                continue;
            }
            for (AbstractRobot robot : this.donneesSimulation.getRobots()) {
                if (robot.isOccupe()) {
                    continue;
                }
                if (robot.peutAller(this.donneesSimulation.getCarte(), incendie.getPosition())) {
                    robot.seChargerDeLincendie(this.donneesSimulation.getCarte(), incendie, this);
                    break;
                }
            }
        }
    }

    private void execute() {
        int i = 0;
        while (i < this.listeEvenements.size() && this.listeEvenements.get(i).getDate() <= this.dateSimulation) {
            if (this.listeEvenements.get(i).getDate() == this.dateSimulation) {
                this.listeEvenements.get(i).execute();
            }
            i++;
        }
    }


    private void draw() {
        guiSimulator.reset();
        for (int i = 0; i < this.donneesSimulation.getCarte().getNbLignes(); i++) {
            for (int j = 0; j < this.donneesSimulation.getCarte().getNbColonnes(); j++) {
                Color color = this.donneesSimulation.getCarte().getCase(i, j).getNatureTerrain().getColor();
                guiSimulator.addGraphicalElement(new Rectangle(j * 100 + 50, i * 100 + 50, Color.white, color, 100));
            }
        }
        for (Incendie incendie : this.donneesSimulation.getIncendies()) {
            guiSimulator.addGraphicalElement(
                    new ImageElement(incendie.getPosition().getColonne() * 100 + 15, incendie.getPosition().getLigne() * 100 + 15, "images/flammes.png", 80, 80, null)
            );
            guiSimulator.addGraphicalElement(
                    new Text(incendie.getPosition().getColonne() * 100 + 50, incendie.getPosition().getLigne() * 100 + 50, Color.RED, Integer.toString(incendie.getEauNecessaire()))
            );
        }

        for (AbstractRobot robot : this.donneesSimulation.getRobots()) {
            guiSimulator.addGraphicalElement(
                    new ImageElement(robot.getPosition().getColonne() * 100 + 20, robot.getPosition().getLigne() *100 + 20, robot.getType().getUrl(), 60, 60, null)
            );
            guiSimulator.addGraphicalElement(
                    new Text(robot.getPosition().getColonne() * 100 + 50, robot.getPosition().getLigne() * 100 + 50, Color.BLACK, Integer.toString(robot.getReservoir()))
            );
        }

    }

    @Override
    public void next() {
        incrementeDate(1);
        execute();
        prendreDecisions();
        draw();
    }

    @Override
    public void restart() {
        this.dateSimulation = 0;
        this.listeEvenements.clear();
        try {
            this.donneesSimulation = LecteurDonnees.lire(fichier);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fichier + " not found");
        } catch (DataFormatException e) {
            System.out.println("Data not in good format");
        }
        prendreDecisions();
        draw();
    }

    private void incrementeDate(int t) {
        this.dateSimulation += t;
    }

    public void ajouteEvenement(Evenement e) {
        int i = 0;
        while (i < this.listeEvenements.size() && e.getDate() > this.listeEvenements.get(i).getDate()) {
            i++;
        }
        this.listeEvenements.add(i, e);
    }

    public long getDateSimulation() {
        return dateSimulation;
    }
}
