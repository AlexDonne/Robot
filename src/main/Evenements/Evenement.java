package Evenements;

public abstract class Evenement {
    private long date;

    public Evenement(long date) {
        this.date = date;
    }

    public long getDate() {
        return this.date;
    }

    public abstract void execute();
}
