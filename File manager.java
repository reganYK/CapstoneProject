import java.io.*;
import java.util.*;

public class FileManager{

    public static void saveLogs(String filename, ArrayList<GoalLog> logs){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (GoalLog log : logs){
                writer.write(log.getDate() + "," + log.getGoal().getName());
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Error saving logs: " + e.getMessage());
        }
    }

    public static ArrayList<GoalLog> loadLogs(String filename, ArrayList<Goal> goalList){
        ArrayList<GoalLog> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",", 2);
                String date = parts[0];
                String goalName = parts[1];
                Goal matchedGoal = findGoalByName(goalList, goalName);
                if (matchedGoal != null){
                    logs.add(new GoalLog(matchedGoal, date));
                }
            }
        } catch (IOException e){
            System.out.println("Error loading logs: " + e.getMessage());
        }
        return logs;
    }

    private static Goal findGoalByName(ArrayList<Goal> goals, String name){
        for (Goal g : goals){
            if (g.getName().equals(name)) return g;
        }
        return null;
    }
}
