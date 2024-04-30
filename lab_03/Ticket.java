import java.util.*;

class Ticket {
    protected int id;
    private String name;
    private int estimate;
    private boolean completed;

    public Ticket(int id, String name, int estimate) {
        this.id = id;
        this.name = name;
        this.estimate = estimate;
        this.completed = false;
    }

    public int gtId() {
        return id;
    }

    public String gtName() {
        return name;
    }

    public int gtEstimate() {
        return estimate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        completed = true;
    }
}