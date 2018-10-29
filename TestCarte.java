public class TestCarte {
  public static void main(String[] args) {
      Carte carte = new Carte(8,8,480.0);
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          carte.ajouterCase(i,j,0);
        }
      }
      carte.ajouterCase(1,4,1);
      carte.ajouterCase(1,6,1);

      carte.ajouterCase(2,2,2);
      carte.ajouterCase(2,3,2);
      carte.ajouterCase(3,2,2);
      carte.ajouterCase(3,3,2);
      carte.ajouterCase(4,2,2);
      carte.ajouterCase(5,2,2);
      carte.ajouterCase(6,2,2);
      carte.ajouterCase(6,3,2);
      carte.ajouterCase(7,3,2);

      carte.ajouterCase(2,5,3);
      carte.ajouterCase(3,5,3);
      carte.ajouterCase(3,6,3);
      carte.ajouterCase(4,5,3);
      carte.ajouterCase(4,6,3);

      carte.ajouterCase(5,5,4);
      carte.ajouterCase(5,6,4);
      carte.ajouterCase(7,1,4);
      carte.ajouterCase(7,2,4);

      // carte.ajouterCase(0,2,2);
      // carte.ajouterCase(1,3,2);

      carte.afficher();
      Graphe graphe = carte.toGraphe(0);
      Graphe graphe1 = carte.toGraphe(1);
      Graphe graphe2 = carte.toGraphe(2);
      Graphe graphe3 = carte.toGraphe(3);
      System.out.println(graphe.dijkstra(17,43)); // drone (pour aller de la case (2,1) à la case (5,3))
      System.out.println(graphe1.dijkstra(17,43)); // roue
      System.out.println(graphe2.dijkstra(17,43)); // chenille
      System.out.println(graphe3.dijkstra(17,43)); // pattes
  }
}
