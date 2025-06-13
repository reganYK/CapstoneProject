import java.util.*;
import java.IOException;

public class EcoPlanner {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static ArrayList<PredefinedGoal> predefinedGoals = new ArrayList<>();

    public static void main(String[] args) {
        setupPredefinedGoals();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        currentUser = new User(name);

	ArrayList<Goal> allKnownGoalsForLoading = new ArrayList<>(predefinedGoals);
        ArrayList<GoalLog> savedLogs = FileManager.loadLogs("logs.txt", allKnownGoalsForLoading);        
	for (GoalLog log : savedLogs) {
            currentUser.logGoalCompletion(log.getGoal(), log.getDate());
        }

        boolean running = true;
        while (running) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: showAllGoals(); break;
                    case 2: logGoal(); break;
                    case 3: showLogs(); break;
                    case 4: running = false; break;
                    default: System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        System.out.println("Thanks for using EcoPlanner!");
	scanner.close();
    }

    private static void setupPredefinedGoals() {
        predefinedGoals.add(new PredefinedGoal("Use Public Transportation", "Use your bicycle, tale the bus, or walk instead of driving."));
        predefinedGoals.add(new PredefinedGoal("Take a Shorter Shower", "Keep your shower under 7 minutes."));
        predefinedGoals.add(new PredefinedGoal("Recycle Properly", "Separate waste and recycle."));
		predefinedGoals.add(new PredefinedGoal("Use Reusable Products", "Use reusable products rather than disposable procuts, like plastic bags."));
        predefinedGoals.add(new PredefinedGoal("Grow a Plant", "Plant an outdoor/indoor plant or vegetable."));
        predefinedGoals.add(new PredefinedGoal("Avoid Food Waste", "Eat everything on your plate."));

    }

    private static void showMenu() {
        System.out.println("\n    EcoPlanner Menu    ");
        System.out.println("1. View Goals");
        System.out.println("2. Log Completed Goal");
        System.out.println("3. View Goal Logs");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void showAllGoals() {
        System.out.println("\nPredefined Goals:");
        for (int i = 0; i < predefinedGoals.size(); i++) {
            System.out.println((i + 1) + ". " + predefinedGoals.get(i));
        }
    }

    private static void logGoal() {
        showAllGoals();
        System.out.print("Select goal number to log: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < predefinedGoals.size()) {
                System.out.print("Enter today's date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                currentUser.logGoalCompletion(predefinedGoals.get(index), date);
                FileManager.saveLogs("logs.txt", currentUser.getLogs());

                if (currentUser.hasStreak()) {
                    System.out.println("Congratulations! You've completed a 30-day streak! A tree will be planted under your name!");
                } else {
                    System.out.println("Goal logged successfully.");
                }
            } else {
                System.out.println("Invalid goal number. Please select a number from the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void showLogs() {
        System.out.println("\nGoal Completion Logs:");
        for (GoalLog log : currentUser.getLogs()) {
            System.out.println(log);
        }
    }

}
