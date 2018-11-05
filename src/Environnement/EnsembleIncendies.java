package Environnement;

import java.util.List;

public class EnsembleIncendies {
  private List<Incendie> listeIncendies;

  public EnsembleIncendies(List<Incendie> listeIncendies) {
    this.listeIncendies = listeIncendies;
  }

  public boolean tousEteints(long t) {
    for (Incendie i : this.listeIncendies) {
      if (!i.estEteint(t)) {
        return false;
      }
    }
    return true;
  }

  public boolean tousPrisEnCharge() {
    for (Incendie i : this.listeIncendies) {
      if (!i.estPrisEnCharge()) {
        return false;
      }
    }
    return true;
  }

  public Incendie incendieLibre() {
    for (Incendie i : this.listeIncendies) {
      if (!i.estPrisEnCharge()) {
        return i;
      }
    }
    return null;
  }

}
