package Evenements;

public abstract class Evenement {

    /**
     * Date à laquelle l'événement doit être exécuté
     */
    protected long date;

    protected Evenement(long date) {
        this.date = date;
    }

    public long getDate() {
        return this.date;
    }

    /**
     * Méthode pour exécuter l'événement, à redéfinir pour chaque classe héritant de cet événement
     */
    public abstract void execute();
}
