import java.util.ArrayList;
import java.util.Collections;

public class Graphe {
  private int n; //nombre de sommets
  private double[][] matriceAdjacence;

  public Graphe(int n) {
    this.n = n;
    this.matriceAdjacence = new double[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          matriceAdjacence[i][j] = 0;
        }
        else {
          matriceAdjacence[i][j] = -1;
        }
      }
    }
  }

  public void ajouterArc(int i, int j, double val) {
    this.matriceAdjacence[i][j] = val;
    this.matriceAdjacence[j][i] = val;
  }

  private static int marquerSommetSuivant(int[] sommetPrec, double[] distance, boolean[] estMarque, int n) {
    int i = 0;
    while ((i < n) && (estMarque[i] || distance[i] < 0)) {
      i++;
    }
    if (i == n) {
      throw new IllegalArgumentException("Plus de sommet à marquer. Le chemin n'existe pas !");
    }
    int k = i;
    double distMin = distance[i];
    for (int j = i + 1; j < n; j++) {
      if (!estMarque[j] && 0 <= distance[j] && distance[j] < distMin)  {
            k = j;
      }
    }
    estMarque[k] = true;
    return k;
  }

  private void mise_a_jour(int[] sommetPrec, double[] distance, boolean[] estMarque, double val, int sommet) {
    for (int j = 0; j < this.n; j++) {
      if (!estMarque[j]) {
        double val1 = this.matriceAdjacence[sommet][j];
        if (val1 >= 0) {
          if (distance[j] < 0) {
            distance[j] = val1 + val;
            sommetPrec[j] = sommet;
          }
          else {
            if (val + val1 < distance[j]) {
              distance[j] = val + val1;
              sommetPrec[j] = sommet;
            }
          }
        }
      }
    }
  }

  public ArrayList<Integer> dijkstra(int a, int b) {
    int[] sommetPrec = new int[this.n]; // donne pour chaque sommet le sommet par lequel il est atteint
    double[] distance = new double[this.n]; // donne pour chaque sommet sa distance au sommet a
    boolean[] estMarque = new boolean[this.n]; // indique pour chaque sommet si le sommet est marque

    // initialisation
    for (int i = 0; i < this.n; i++) {
      sommetPrec[i] = -1;
      distance[i] = -1;
      estMarque[i] = false;
    }
    sommetPrec[a] = a;
    distance[a] = 0;
    estMarque[a] = true;

    // corps de l'algorithme
    int c = a;
    double val = 0;

    while (c != b) {
      this.mise_a_jour(sommetPrec, distance, estMarque, val, c);
      c = Graphe.marquerSommetSuivant(sommetPrec, distance, estMarque, this.n);
      val = distance[c];
    }


    // On retourne enfin la liste des sommets par lesquels passer
    ArrayList<Integer> liste = new ArrayList<Integer>();
    int sommet = b;
    while(sommet != a) {
      liste.add(sommet);
      sommet = sommetPrec[sommet];
    }
    liste.add(a);
    Collections.reverse(liste);
    return liste;
  }

  public void afficher() {
    for (int i = 0; i < this.n; i++) {
      for(int j = 0; j < this.n; j++) {
        System.out.print(this.matriceAdjacence[i][j]);
        System.out.print(" ; ");
      }
      System.out.println();
    }
  }


}
