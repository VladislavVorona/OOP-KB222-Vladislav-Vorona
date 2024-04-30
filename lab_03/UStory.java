import java.util.*;

class UserStory extends Ticket {
    private List<Bug> bugs;
    private List<UserStory> depends;

    public UserStory(int id, String name, int estimate) {
        super(id, name, estimate);
        this.bugs = new ArrayList<>();
        this.depends = new ArrayList<>();
    }

    public List<Bug> gtBugs() {
        return new ArrayList<>(bugs);
    }

    public boolean addBug(Bug bug) {
        if (bug != null && !bug.isCompleted()) {
            bugs.add(bug);
            return true;
        }
        return false;
    }

    public void addDepend(UserStory depend) {
        if (depend != null) {
            depends.add(depend);
        }
    }

    public void remDepend(UserStory depend) {
        depends.remove(depend);
    }

    public List<UserStory> gtdepends() {
        return new ArrayList<>(depends);
    }

    @Override
    public String toString() {
        return "[US " + id + "] Назва: " + gtName() + ", Оцінка: " + gtEstimate() + ", Завершено: " + isCompleted();
    }
}