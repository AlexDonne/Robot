package io;

import Robots.AbstractRobot;
import Environnement.Carte;
import Environnement.Incendie;

import java.util.List;
import java.util.Set;

public class DonneesSimulation {
    private Carte carte;

    private Set<Incendie> incendies;

    private List<AbstractRobot> robots;

    public DonneesSimulation(Carte carte, Set<Incendie> incendies, List<AbstractRobot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public Set<Incendie> getIncendies() {
        return incendies;
    }

    public List<AbstractRobot> getRobots() {
        return robots;
    }
}
