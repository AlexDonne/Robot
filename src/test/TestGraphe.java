public class TestGraphe {
  public static void main(String[] args) {

    // double[] distance = {0,-1,-1,-1,-1,-1,-1};
    // int[] sommetPrec = {0,-1,-1,-1,-1,-1,-1};
    // boolean[] estMarque = {true, false, false, false, false, false, false};

    int n = 7;
    Graphe graphe = new Graphe(n);
    graphe.ajouterArc(1,0,1.1);
    graphe.ajouterArc(2,0,2);
    graphe.ajouterArc(3,1,2);
    graphe.ajouterArc(5,1,3);
    graphe.ajouterArc(3,2,3);
    graphe.ajouterArc(4,2,4);
    graphe.ajouterArc(4,3,2);
    graphe.ajouterArc(5,3,3);
    graphe.ajouterArc(6,3,3);
    graphe.ajouterArc(6,4,5);
    graphe.ajouterArc(6,5,4);
    graphe.afficher();
    Itineraire iti = graphe.dijkstra(0,6);
    iti.afficher();
    //System.out.println(graphe.dijkstra(0,6));
  }
}
