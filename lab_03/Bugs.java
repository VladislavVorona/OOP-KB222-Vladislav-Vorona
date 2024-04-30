import java.util.*;

class Bug extends Ticket {
    private UserStory ustory;

    private Bug(int id, String name, int estimate, UserStory ustory) {
        super(id, name, estimate);
        this.ustory = ustory;
    }

    public static Bug createBug(int id, String name, int estimate, UserStory ustory) {
        if (ustory != null && !ustory.isCompleted()) {
            return new Bug(id, name, estimate, ustory);
        }
        return null;
    }

    public UserStory gtUStory() {
        return ustory;
    }

    @Override
    public String toString() {
        return "[BUG " + id + "] Назва: " + gtName() + ", Оцінка: " + gtEstimate() + ", ID історії користувача: " + ustory.gtId() + ", Завершено: " + isCompleted();
    }
}