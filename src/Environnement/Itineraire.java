package Environnement;

import java.util.List;
import java.util.Map;

/**
 * Classe qui représente l'itinéraire d'un robot pour aller à une case
 */
public class Itineraire {

  /**
   * Contient une liste d'éléments itinéraires, qui associe une case au temps que le robot mettre pour y aller
   */
  private List<ElementItineraire> mapItineraire;

  public Itineraire(List<ElementItineraire> mapItineraire) {
    this.mapItineraire = mapItineraire;
  }

  public List<ElementItineraire> getMapItineraire() {
    return this.mapItineraire;
  }

  @Override
  public String toString() {
    String message = new String();
    for (ElementItineraire e : this.mapItineraire) {
      message += e.toString();
    }
    return message;
  }

}
