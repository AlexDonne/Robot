import java.util.List;
import java.util.ArrayList;

public class Carte {
  private double tailleCases;
  private int nbLignes;
  private int nbColonnes;
  private int[][] matrice; // matrice contenant 0, 1, 2, 3 ou 4 en fonction du terrain (respectivement: libre, roche, eau, foret, habitat)

  public Carte(int nbLignes, int nbColonnes, double tailleCases) {
    this.nbLignes = nbLignes;
    this.nbColonnes = nbColonnes;
    this.tailleCases = tailleCases;
    this.matrice = new int[nbLignes][nbColonnes];
  }

  public void ajouterCase(int i, int j, int nature) {
    matrice[i][j] = nature;
  }

  public void afficher() {
    System.out.println("Voci la carte: (0: libre, 1: roche, 2: eau, 3: forêt, 4: habitat)");
    for (int i = 0; i < this.nbLignes; i++) {
      for(int j = 0; j < this.nbColonnes; j++) {
        System.out.print(this.matrice[i][j]);
        System.out.print(" ; ");
      }
      System.out.println();
    }
  }

  public Graphe toGraphe(int typeRobot) { // passer en argument le type du robot 0, 1, 2 ou 3 (respectivement drone, roue, chenille, pattes)
    int[][] matriceVitesse = {{100, 100, 100, 100, 100}, {80, 0, 0, 0, 80}, {60, 0, 0, 30, 60}, {30, 10, 0, 30, 30}};
    Graphe graphe = new Graphe(this.nbLignes * this.nbColonnes);
    for (int i = 0; i < nbLignes; i++) {
      for (int j = 1; j < nbColonnes; j++) {
        if (matriceVitesse[typeRobot][this.matrice[i][j-1]] > 0  && matriceVitesse[typeRobot][this.matrice[i][j]] > 0) {
          double temps = 0;
          temps += this.tailleCases / matriceVitesse[typeRobot][this.matrice[i][j-1]] / 2;
          temps += this.tailleCases / matriceVitesse[typeRobot][this.matrice[i][j]] / 2;
          graphe.ajouterArc(i * nbColonnes + j - 1, i * nbColonnes + j, temps);
        }
      }
    }
    for (int j = 0; j < nbColonnes; j++) {
      for (int i = 1; i < nbLignes; i++) {
        if (matriceVitesse[typeRobot][this.matrice[i-1][j]] > 0  && matriceVitesse[typeRobot][this.matrice[i][j]] > 0) {
          double temps = 0;
          temps += this.tailleCases / matriceVitesse[typeRobot][this.matrice[i-1][j]] / 2;
          temps += this.tailleCases / matriceVitesse[typeRobot][this.matrice[i][j]] / 2;
          graphe.ajouterArc((i-1) * nbColonnes + j, i * nbColonnes + j, temps);
        }
      }
    }
    return graphe;
  }
}
