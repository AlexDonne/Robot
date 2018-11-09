package Evenements;

public abstract class Evenement {
    protected long date;

    protected Evenement(long date) {
        this.date = date;
    }

    public long getDate() {
        return this.date;
    }

    public abstract void execute();
}
