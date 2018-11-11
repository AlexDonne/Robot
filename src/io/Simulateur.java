package io;

import Decision.ChefPompier;
import Decision.IStrategieGlobale;
import Environnement.Incendie;
import Evenements.Evenement;
import Robots.AbstractRobot;
import Robots.StrategieDeplacement.IStrategieDeplacement;
import gui.*;
import gui.Rectangle;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.concurrent.TimeUnit;


public class Simulateur implements Simulable {

    /**
     * Liste des événements
     */
    private Queue<Evenement> listeEvenements;

    private GUISimulator guiSimulator;

    private long dateSimulation;

    /**
     * Les données de la simulation
     */
    private DonneesSimulation donneesSimulation;

    /**
     * Le chef des pompiers pour la simulation
     */
    private ChefPompier chefPompier;
    /**
     * Nom du fichier de la map
     */
    private String fichier;

    /**
     * La stratégie de déplacement pour les robots (en attribut pour qu'on la sauvegarde, nécessaire pour le reset)
     */
    private IStrategieDeplacement strategieDeplacement;

    private float coeff;

    /**
     * La date de début de la simulation (utile pour le restart)
     */
    private long dateDebut;

    /**
     * @param dateSimulation, date de départ de la simulation
     * @param fichier,        le nom du fichier de la map
     * @param strategie,      la stratégie que va adopter le chef pompier
     */
    public Simulateur(long dateSimulation, String fichier, IStrategieGlobale strategie, IStrategieDeplacement strategieDeplacement) {
        this.fichier = fichier;
        this.dateSimulation = dateSimulation;
        this.dateDebut = dateSimulation;
        this.strategieDeplacement = strategieDeplacement;

        Comparator<Evenement> evenementComparator = Comparator.comparingDouble(Evenement::getDate);
        this.listeEvenements = new PriorityQueue<>(evenementComparator);

        try {
            this.donneesSimulation = LecteurDonnees.lire(fichier, strategieDeplacement);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fichier + " not found");
        } catch (DataFormatException e) {
            System.out.println("Data not in good format");
        }
        this.coeff = 10 / (float) this.donneesSimulation.getCarte().getNbLignes();
        this.guiSimulator = new GUISimulator(this.donneesSimulation.getCarte().getNbLignes() * 100, this.donneesSimulation.getCarte().getNbColonnes() * 100, Color.BLACK);
        guiSimulator.setSimulable(this);
        this.chefPompier = new ChefPompier(this.donneesSimulation, strategie);
    }

    /**
     * Initialise la simulation, lance la prise des premières decisions et dessine l'interface
     */
    public void start() {
        sketch();
        this.chefPompier.prendreDecisions(this);
        draw();
    }


    /**
     * Execute tous les evenements dont la date est inférieur à la dete de simulation, les supprime ensuite de la liste (pour ne pas les réexecuter)
     */
    private void execute() {
        while (!this.listeEvenements.isEmpty() && this.listeEvenements.peek().getDate() <= this.dateSimulation) {
            this.listeEvenements.poll().execute();
        }
    }


    /**
     * Affiche l'écran de démarrage
     */

    private void sketch() {
        guiSimulator.reset();

        guiSimulator.addGraphicalElement(
                new ImageElement(
                        0,
                        0,
                        "images/pattes.png",
                        350,
                        350,
                        null
                )
        );
        guiSimulator.addGraphicalElement(
                new ImageElement(
                        470,
                        0,
                        "images/roues.png",
                        300,
                        350,
                        null
                )
        );
        guiSimulator.addGraphicalElement(
                new ImageElement(
                        940,
                        0,
                        "images/chenilles.png",
                        300,
                        350,
                        null
                )
        );
        guiSimulator.addGraphicalElement(
                new ImageElement(
                        1410,
                        0,
                        "images/drone.png",
                        300,
                        350,
                        null
                )
        );

        guiSimulator.addGraphicalElement(
                new ImageElement(
                        360,
                        450,
                        "images/title.png",
                        1003,
                        186,
                        null
                )
        );

        guiSimulator.addGraphicalElement(
                new ImageElement(
                        0,
                        650,
                        "images/flammes.png",
                        300,
                        350,
                        null
                )
        );

        guiSimulator.addGraphicalElement(
                new ImageElement(
                        650,
                        750,
                        this.fichier.replaceAll(".map", ".png").replaceAll("cartes/", "images/"),
                        500,
                        150,
                        null
                )
        );

        guiSimulator.addGraphicalElement(
                new ImageElement(
                        1410,
                        650,
                        "images/flammes.png",
                        300,
                        350,
                        null
                )
        );
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Affiche l'écran de fin
     */

    private void end() {
        guiSimulator.addGraphicalElement(
                new ImageElement(
                        400,
                        450,
                        "images/end.png",
                        902,
                        186,
                        null
                )
        );
        guiSimulator.addGraphicalElement(
                new Text(
                        100,
                        100,
                        Color.BLACK,
                        "Temps : " + Long.toString(this.dateSimulation - this.dateDebut)
                )
        );
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Dessine l'interface
     */
    private void draw() {
        guiSimulator.reset();
        for (int i = 0; i < this.donneesSimulation.getCarte().getNbLignes(); i++) {
            for (int j = 0; j < this.donneesSimulation.getCarte().getNbColonnes(); j++) {
                Color color = this.donneesSimulation.getCarte().getCase(i, j).getNatureTerrain().getColor();
                guiSimulator.addGraphicalElement(
                        new Rectangle(
                                Math.round(this.coeff * (j * 100 + 50)),
                                Math.round(this.coeff * (i * 100 + 50)),
                                Color.white,
                                color,
                                Math.round(this.coeff * 100)
                        )
                );
            }
        }
        for (Incendie incendie : this.donneesSimulation.getIncendies()) {
            if (incendie.estEteint()) {
                guiSimulator.addGraphicalElement(
                        new ImageElement(
                                Math.round(this.coeff * (incendie.getPosition().getColonne() * 100 + 15)),
                                Math.round(this.coeff * (incendie.getPosition().getLigne() * 100 + 15)),
                                "images/smoke.png",
                                Math.round(this.coeff * 80),
                                Math.round(this.coeff * 80),
                                null
                        )
                );
            } else {
                guiSimulator.addGraphicalElement(
                        new ImageElement(
                                Math.round(this.coeff * (incendie.getPosition().getColonne() * 100 + 15)),
                                Math.round(this.coeff * (incendie.getPosition().getLigne() * 100 + 15)),
                                "images/flammes.png",
                                Math.round(this.coeff * 80),
                                Math.round(this.coeff * 80),
                                null
                        )
                );
                guiSimulator.addGraphicalElement(
                        new Text(
                                Math.round(this.coeff * (incendie.getPosition().getColonne() * 100 + 50)),
                                Math.round(this.coeff * (incendie.getPosition().getLigne() * 100 + 10)),
                                Color.WHITE,
                                Integer.toString(incendie.getEauNecessaire())
                        )
                );
            }
        }

        for (AbstractRobot robot : this.donneesSimulation.getRobots()) {
            guiSimulator.addGraphicalElement(
                    new ImageElement(
                            Math.round(this.coeff * (robot.getPosition().getColonne() * 100 + 20)),
                            Math.round(this.coeff * (robot.getPosition().getLigne() * 100 + 20)),
                            robot.getType().getUrl(),
                            Math.round(this.coeff * 60),
                            Math.round(this.coeff * 60),
                            null
                    )
            );
            guiSimulator.addGraphicalElement(
                    new Text(
                            Math.round(this.coeff * (robot.getPosition().getColonne() * 100 + 50)),
                            Math.round(this.coeff * (robot.getPosition().getLigne() * 100 + 90)),
                            Color.BLACK,
                            Integer.toString(robot.getReservoir())
                    )
            );
        }

    }

    /**
     * Execute les événements dont la date est inférieur à la date de simulation, prend les nouvelles décisions, dessine l'interface, incrémente la date et teste si c'est la fin
     * Si c'est la fin appelle la méthode end
     */
    @Override
    public void next() {
        System.out.println("Time : " + this.dateSimulation);
        execute();
        this.chefPompier.prendreDecisions(this);
        draw();
        incrementeDate(60);
        boolean fin = true;
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            if (!incendie.estEteint()) {
                fin = false;
            }
        }
        if (fin) {
            end();
        }
    }

    /**
     * Repart comme en début de simulation
     */
    @Override
    public void restart() {
        this.dateSimulation = this.dateDebut;
        this.listeEvenements.clear();
        try {
            this.donneesSimulation = LecteurDonnees.lire(fichier, this.strategieDeplacement);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fichier + " not found");
        } catch (DataFormatException e) {
            System.out.println("Data not in good format");
        }
        this.chefPompier.prendreDecisions(this);
        draw();
    }

    /**
     * Incrémente la date de la simulation
     *
     * @param temps
     */
    private void incrementeDate(int temps) {
        this.dateSimulation += temps;
    }

    /**
     * Ajoute un événement
     *
     * @param e
     */
    public void ajouteEvenement(Evenement e) {
        this.listeEvenements.add(e);
    }

    public long getDateSimulation() {
        return dateSimulation;
    }
}
