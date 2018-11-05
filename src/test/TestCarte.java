import Environnement.Carte;
import Environnement.Case;
import Environnement.Graphe;
import Environnement.NatureTerrain;

public class TestCarte {
  public static void main(String[] args) {
      Carte carte = new Carte(8,8,480.0);
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          carte.ajouterCase(i,j,new Case(NatureTerrain.TERRAIN_LIBRE));
        }
      }
      carte.ajouterCase(1,4,new Case(NatureTerrain.ROCHE));
      carte.ajouterCase(1,6,new Case(NatureTerrain.ROCHE));

      carte.ajouterCase(2,2,new Case(NatureTerrain.EAU));
      carte.ajouterCase(2,3,new Case(NatureTerrain.EAU));
      carte.ajouterCase(3,2,new Case(NatureTerrain.EAU));
      carte.ajouterCase(3,3,new Case(NatureTerrain.EAU));
      carte.ajouterCase(4,2,new Case(NatureTerrain.EAU));
      carte.ajouterCase(5,2,new Case(NatureTerrain.EAU));
      carte.ajouterCase(6,2,new Case(NatureTerrain.EAU));
      carte.ajouterCase(6,3,new Case(NatureTerrain.EAU));
      carte.ajouterCase(7,3,new Case(NatureTerrain.EAU));

      carte.ajouterCase(2,5,new Case(NatureTerrain.FORET));
      carte.ajouterCase(3,5,new Case(NatureTerrain.FORET));
      carte.ajouterCase(3,6,new Case(NatureTerrain.FORET));
      carte.ajouterCase(4,5,new Case(NatureTerrain.FORET));
      carte.ajouterCase(4,6,new Case(NatureTerrain.FORET));

      carte.ajouterCase(5,5,new Case(NatureTerrain.HABITAT));
      carte.ajouterCase(5,6,new Case(NatureTerrain.HABITAT));
      carte.ajouterCase(7,1,new Case(NatureTerrain.HABITAT));
      carte.ajouterCase(7,2,new Case(NatureTerrain.HABITAT));
  }
}
