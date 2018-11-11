package io;


import Environnement.*;
import Robots.*;
import Environnement.NatureTerrain;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

import Robots.StrategieDeplacement.IStrategieDeplacement;
import Robots.StrategieDeplacement.StrategieDeplacementDijkstra;
import Robots.TypesRobot;


/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier, affichées et instanciées.
 * Sert à créer l'objet DonneesSimulation retourné
 */
public class LecteurDonnees {

    private Carte carte;

    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Construit les différents objets (robots, incendies, carte ..) et créé un objet DonneesSimulation les contenant, puis le retourne
     *
     * @param fichierDonnees nom du fichier à lire
     * @return DonneesSimulation
     */
    public static DonneesSimulation lire(String fichierDonnees, IStrategieDeplacement strategieDeplacement)
            throws FileNotFoundException, DataFormatException {
        System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
        DonneesSimulation donneesSimulation = new DonneesSimulation(lecteur.lireCarte(), lecteur.lireIncendies(), lecteur.lireRobots(strategieDeplacement));
        scanner.close();
        System.out.println("\n == Lecture terminee");

        return donneesSimulation;
    }


    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     *
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
            throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit, affiche les donnees de la carte et retourne l'objet Carte.
     *
     * @return Carte
     * @throws DataFormatException
     */
    private Carte lireCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();    // en m

            Carte carte = new Carte(nbLignes, nbColonnes, tailleCases);
            System.out.println("Carte " + nbLignes + "x" + nbColonnes
                    + "; taille des cases = " + tailleCases);

            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    NatureTerrain nature = lireCase(lig, col);
                    carte.ajouterCase(lig, col, nature);
                }
            }
            this.carte = carte;
            return carte;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }


    /**
     * Lit, affiche les donnees d'une case et retourne l'objet Case
     *
     * @return Case
     */
    private NatureTerrain lireCase(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Case (" + lig + "," + col + "): ");
        String chaineNature = new String();

        try {
            chaineNature = scanner.next();
            // si NatureTerrain est un Enum, vous pouvez recuperer la valeur
            // de l'enum a partir d'une String avec:
            //			NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

            verifieLigneTerminee();

            System.out.print("nature = " + chaineNature);

            return NatureTerrain.valueOf(chaineNature);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }
    }


    /**
     * Lit, affiche les donnees des incendies et les retourne dans une liste
     *
     * @return List<Incendie>
     */
    private List<Incendie> lireIncendies() throws DataFormatException {
        List<Incendie> incendies = new ArrayList<>();
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                incendies.add(lireIncendie(i));
            }

            return incendies;
        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /**
     * Lit,affiche les donnees du i-eme incendie et le retourne.
     *
     * @param i
     * @return Incendie
     */
    private Incendie lireIncendie(int i) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Environnement.Incendie " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

            System.out.println("position = (" + lig + "," + col
                    + ");\t intensite = " + intensite);

            return new Incendie(this.carte.getCase(lig, col), intensite);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Lit, affiche les donnees des robots et retourne la liste des robots
     *
     * @return List<AbstractRobot>
     */
    private List<AbstractRobot> lireRobots(IStrategieDeplacement strategieDeplacement) throws DataFormatException {
        ignorerCommentaires();
        List<AbstractRobot> robots = new ArrayList<>();
        try {
            int nbRobots = scanner.nextInt();
            System.out.println("Nb de robots = " + nbRobots);
            for (int i = 0; i < nbRobots; i++) {
                robots.add(lireRobot(i, strategieDeplacement));
            }

            return robots;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /**
     * Lit, affiche les donnees du i-eme robot et le construit
     *
     * @param i
     * @return AbstractRobot
     */
    private AbstractRobot lireRobot(int i, IStrategieDeplacement strategieDeplacement) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Robot " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            System.out.print("position = (" + lig + "," + col + ");");
            String type = scanner.next();

            System.out.print("\t type = " + type);


            // lecture eventuelle d'une vitesse du robot (entier)
            System.out.print("; \t vitesse = ");
            String s = scanner.findInLine("(\\d+)");    // 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");
            int vitesse = -1;
            if (s == null) {
                System.out.print("valeur par defaut");
            } else {
                vitesse = Integer.parseInt(s);
                System.out.print(vitesse);
            }
            verifieLigneTerminee();

            System.out.println();

            TypesRobot typeRobot = TypesRobot.valueOf(type);
            AbstractRobot robot;
            switch (typeRobot) {
                case ROUES:
                    robot = new RobotRoues(this.carte.getCase(lig, col), strategieDeplacement);
                    break;

                case DRONE:
                    robot = new RobotAerien(this.carte.getCase(lig, col), strategieDeplacement);
                    break;
                case CHENILLES:
                    robot = new RobotChenilles(this.carte.getCase(lig, col), strategieDeplacement);
                    break;
                case PATTES:
                    robot = new RobotPattes(this.carte.getCase(lig, col), strategieDeplacement);
                    break;
                default:
                    throw new DataFormatException("Type non connu");
            }
            if (vitesse != -1) {
                robot.setVitesse(vitesse * 1000);
            }

            return robot;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
    }


    /**
     * Ignore toute (fin de) ligne commencant par '#'
     */
    private void ignorerCommentaires() {
        while (scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     *
     * @throws DataFormatException
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
