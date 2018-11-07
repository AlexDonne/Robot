package Environnement;

import java.util.Map;

public class Itineraire {

  private Map<Case, Double> mapItineraire;

  public Itineraire(Map<Case, Double> mapItineraire) {
    this.mapItineraire = mapItineraire;
  }

  public Map<Case, Double> getMapItineraire() {
    return this.mapItineraire;
  }

}
