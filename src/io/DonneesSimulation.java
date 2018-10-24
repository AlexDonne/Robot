package io;

import Entities.AbstractRobot;
import Entities.Carte;
import Entities.Incendie;

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
}
