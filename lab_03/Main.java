import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner inpScn = new Scanner(System.in);
        int capxx = 0;
        int ticksLim = 0;

        while (true) {
            try {
                System.out.println("Введіть обмеження спринту");
                System.out.println("Доступна кількість балів:");
                capxx = inpScn.nextInt();
                System.out.println("Доступна кількість тікетів:");
                ticksLim = inpScn.nextInt();
                inpScn.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Було введено некоректне значення, спробуйте ще раз.");
                inpScn.nextLine();
            }
        }

        Sprint sprint = new Sprint(capxx, ticksLim);

        System.out.println("Команди:");
        System.out.println("A: Додати тікет");
        System.out.println("C: Позначити тікет, як завершений");
        System.out.println("D: Додати залежність тікету");
        System.out.println("P: Вивести всі тікети");
        System.out.println("H: Показати доступні команди");
        System.out.println("X: Вийти");

        boolean xx = true;
        while (xx) {
            System.out.print("\nВведіть команду: ");
            String command = inpScn.nextLine().toUpperCase();

            try {    
                switch (command) {
                    case "A":
                        System.out.print("Введіть тип тікета (U для UserStory, B для Bug): ");
                        String type = inpScn.nextLine().toUpperCase();
                        if (type.equals("U")) {
                            System.out.println("Введіть деталі історії (ID, назва, оцінка):");
                            int id = inpScn.nextInt();
                            String name = inpScn.next();
                            int estimate = inpScn.nextInt();
                            inpScn.nextLine();
                            UserStory ustory = new UserStory(id, name, estimate);
                            if (sprint.addUserStory(ustory)) {
                                System.out.println("Історію користувача додано: " + ustory);
                            } else {
                                System.out.println("Не вдалося додати історію користувача. Перевірте, чи існує ID історії користувача або чи вона вже завершена.");
                            }
                        } else if (type.equals("B")) {
                            System.out.println("Введіть деталі помилки (ID, назва, оцінка, ID історії користувача):");
                            int id = inpScn.nextInt();
                            String name = inpScn.next();
                            int estimate = inpScn.nextInt();
                            int usid = inpScn.nextInt();
                            inpScn.nextLine();
                            UserStory ustory = findUS(sprint.gtTickets(), usid);
                            if (ustory != null) {
                                Bug bug = Bug.createBug(id, name, estimate, ustory);
                                if (bug != null && sprint.addBug(bug)) {
                                    System.out.println("Помилку додано: " + bug);
                                } else {
                                    System.out.println("Не вдалося додати баг. Перевірте, чи існує ID помилки, ID історії користувача, або чи історію користувача вже завершено.");
                                }
                            } else {
                                System.out.println("Історію користувача з ID " + usid + " не знайдено.");
                            }
                        } else {
                            System.out.println("Неправильний тип тікета. Будь ласка, спробуйте знову.");
                        }
                        break;
                    case "D":
                        System.out.print("Введіть ID історії користувача для управління залежностями: ");
                        int usid = inpScn.nextInt();
                        inpScn.nextLine();
                        UserStory targtUserStory = findUS(sprint.gtTickets(), usid);
                        if (targtUserStory != null) {
                            System.out.print("Введіть ID залежності для додавання або видалення (0 для скасування): ");
                            int dependId = inpScn.nextInt();
                            inpScn.nextLine();
                            if (dependId != 0) {
                                UserStory depend = findUS(sprint.gtTickets(), dependId);
                                if (depend != null) {
                                    if (!targtUserStory.gtdepends().contains(depend)) {
                                        targtUserStory.addDepend(depend);
                                        System.out.println("Залежність додано: " + depend);
                                    } else {
                                        targtUserStory.remDepend(depend);
                                        System.out.println("Залежність видалено: " + depend);
                                    }
                                } else {
                                    System.out.println("Історію користувача з ID " + dependId + " не знайдено.");
                                }
                            }
                        } else {
                            System.out.println("Історію користувача з ID " + usid + " не знайдено.");
                        }
                        break;
                    case "C":
                        System.out.print("Введіть ID тікета для позначення, як завершений: ");
                        int ticketId = inpScn.nextInt();
                        inpScn.nextLine();
                        if (sprint.completeTicket(ticketId)) {
                            System.out.println("Тікет з ID " + ticketId + " позначено, як завершений.");
                        }
                        break;
                    case "P":
                        List<Ticket> sortTicks = new ArrayList<>(sprint.gtTickets());
                        sortTicks.sort(Comparator.comparingInt(Ticket::gtId));
                        int totalEst = 0;
                        int remPoints = sprint.gtCapacity() - sprint.gtTotalEst();
                        int totalTicks = 0;
                        int completTicks = 0;
                        int remTicks = sprint.gtTickLimit();

                        for (Ticket ticket : sortTicks) {
                            totalTicks++;

                            if (ticket.isCompleted()) {
                                completTicks++;
                            }

                            if (ticket instanceof UserStory) {
                                System.out.println(ticket);
                                UserStory ustory = (UserStory) ticket;
                                List<Bug> bugs = new ArrayList<>();
                                List<UserStory> depends = ustory.gtdepends();
                                for (Ticket t : sortTicks) {
                                    if (t instanceof Bug && ((Bug) t).gtUStory().gtId() == ustory.gtId()) {
                                        bugs.add((Bug) t);
                                    }
                                }
                                bugs.sort(Comparator.comparingInt(Bug::gtId));
                                for (Bug bug : bugs) {
                                    System.out.println("    " + bug);
                                }
                                for (UserStory depend : depends) {
                                    System.out.println("    Залежність: " + depend);
                                    List<Bug> dependBugs = new ArrayList<>();
                                    for (Ticket t : sortTicks) {
                                        if (t instanceof Bug && ((Bug) t).gtUStory().gtId() == depend.gtId()) {
                                            dependBugs.add((Bug) t);
                                        }
                                    }
                                    dependBugs.sort(Comparator.comparingInt(Bug::gtId));
                                    for (Bug xxBug : dependBugs) {
                                        System.out.println("        " + xxBug);
                                    }
                                }
                            }
                        }
                        System.out.println("----------------------------------");
                        System.out.println("Загальна оцінка: " + sprint.gtTotalEst());
                        System.out.println("Залишилось балів: " + remPoints);
                        System.out.println("Загальна кількість тікетів: " + totalTicks);
                        System.out.println("Завершені тікети: " + completTicks);
                        remTicks -= totalTicks - completTicks;
                        System.out.println("Залишилося тікетів для створення: " + remTicks);
                        break;

                    case "H":
                        System.out.println("Команди:");
                        System.out.println("A: Додати тікет");
                        System.out.println("C: Позначити тікет, як завершений");
                        System.out.println("D: Додати залежність тікету");
                        System.out.println("P: Вивести всі тікети");
                        System.out.println("H: Показати доступні команди");
                        System.out.println("X: Вийти");
                        break;
                    case "X":
                        xx = false;
                        break;
                    default:
                        System.out.println("Невірна команда, спробуйте знову.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неправильний ввід, потрібно вводити число.");
            }
        }

        inpScn.close();
    }

    private static UserStory findUS(List<Ticket> ticks, int id) {
        for (Ticket ticket : ticks) {
            if (ticket instanceof UserStory && ticket.gtId() == id) {
                return (UserStory) ticket;
            }
        }
        return null;
    }
}
