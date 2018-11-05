package Environnement;

import java.util.ArrayList;

public class Itineraire {
  private ArrayList<Integer> listeSommets;
  private ArrayList<Double> listeDurees;

  public Itineraire(ArrayList<Integer> listeSommets, ArrayList<Double> listeDurees) {
    this.listeSommets = listeSommets;
    this.listeDurees = listeDurees;
  }

  public ArrayList<Integer> getListeSommets() {
    return this.listeSommets;
  }

  public ArrayList<Double> getListeDurees() {
    return this.listeDurees;
  }

  public void afficher() {
    System.out.println("Liste des sommets : " + this.listeSommets);
    System.out.println("Temps pour chacun des sommets : " + this.listeDurees);
  }
}
