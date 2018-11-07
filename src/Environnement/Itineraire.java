package Environnement;

import java.util.List;
import java.util.Map;

public class Itineraire {

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
