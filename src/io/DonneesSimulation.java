package io;

import Robots.AbstractRobot;
import Environnement.Carte;
import Environnement.Incendie;

import java.util.List;
import java.util.Set;

public class DonneesSimulation {
    private Carte carte;

    private List<Incendie> incendies;

    private List<AbstractRobot> robots;

    public DonneesSimulation(Carte carte, List<Incendie> incendies, List<AbstractRobot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public List<Incendie> getIncendies() {
        return incendies;
    }

    public List<AbstractRobot> getRobots() {
        return robots;
    }
}
