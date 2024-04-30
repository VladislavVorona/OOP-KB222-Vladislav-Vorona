import java.util.*;

class Sprint {
    private int capacity;
    private int ticksLimit;
    private List<Ticket> ticks;

    public Sprint(int capacity, int ticksLimit) {
        this.capacity = capacity;
        this.ticksLimit = ticksLimit;
        this.ticks = new ArrayList<>();
    }

    public boolean addUserStory(UserStory ustory) {
        int noComplete = calcNoComplTicks();
        int totalEst = gtTotalEst() + ustory.gtEstimate();
        if (ustory != null && !exists(ustory) && !ustory.isCompleted() && noComplete < ticksLimit && totalEst <= capacity) {
            ticks.add(ustory);
            return true;
        }
        return false;
    }

    public boolean addBug(Bug bug) {
        int noComplete = calcNoComplTicks();
        int totalEst = gtTotalEst() + bug.gtEstimate();
        if (bug != null && !exists(bug) && !bug.isCompleted() && noComplete < ticksLimit && totalEst <= capacity) {
            ticks.add(bug);
            return true;
        }
        return false;
    }

    public int gtCapacity() {
        return capacity;
    }

    private int calcNoComplTicks() {
        int count = 0;
        for (Ticket ticket : ticks) {
            if (!ticket.isCompleted()) {
                count++;
            }
        }
        return count;
    }

    public int gtTickLimit() {
        return ticksLimit;
    }

    public boolean completeTicket(int ticketId) {
        for (Ticket ticket : ticks) {
            if (ticket.gtId() == ticketId && !ticket.isCompleted()) {
                UserStory ustory = null;
                if (ticket instanceof UserStory) {
                    ustory = (UserStory) ticket;
                }
                if (ustory != null) {
                    List<UserStory> depends = ustory.gtdepends();
                    for (UserStory depend : depends) {
                        if (!depend.isCompleted()) {
                            System.out.println("Не вдалося завершити історію користувача з ID " + ticketId + ", оскільки є невирішені залежності.");
                            return false;
                        }
                    }
                }
                for (Ticket t : ticks) {
                    if (t instanceof Bug && !t.isCompleted() && ((Bug) t).gtUStory().gtId() == ticketId) {
                        System.out.println("Не вдалося завершити історію користувача з ID " + ticketId + ", оскільки є невирішені помилки.");
                        return false;
                    }
                }
                ticket.complete();
                return true;
            }
        }
        System.out.println("Не вдалося знайти тікет з ID " + ticketId + ", або він вже завершений.");
        return false;
    }

    public List<Ticket> gtTickets() {
        return new ArrayList<>(ticks);
    }

    public int gtTotalEst() {
        int totalEst = 0;
        for (Ticket ticket : ticks) {
            if (!ticket.isCompleted()) {
                totalEst += ticket.gtEstimate();
            }
            if (ticket instanceof UserStory && ticket.isCompleted()) {
                UserStory ustory = (UserStory) ticket;
                List<Bug> bugs = ustory.gtBugs();
                for (Bug bug : bugs) {
                    if (!bug.isCompleted()) {
                        totalEst += bug.gtEstimate();
                    }
                }
            }
        }
        return totalEst;
    }

    private boolean exists(Ticket ticket) {
        for (Ticket t : ticks) {
            if (t.gtId() == ticket.gtId()) {
                return true;
            }
        }
        return false;
    }
}