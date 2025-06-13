import java.io.*;
import java.util.*;

/** 
* This class saves and loads goal logs to/from a file
*/
public class FileManager{

    /**
    * Saves a list of GoalLog objects to a file.
    * Each log is written on a new line in the format: date,goalName
    * @param filename the name of the file to save to
    * @param logs the list of GoalLog objects to be saved
    */
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

    /**
    * Loads GoalLog from a file.
    * Matches each goal name in the file with a Goal from the provided list.
    * @param filename the name of the file to read from
    * @param goalList the list of known goals to match against the logs
    * @return a list of GoalLog objects created from the file data
    */
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

    /**
    * Searches for a goal in the list by its name.
    * @param goals the list of Goal objects
    * @param name the name to search for
    * @return the Goal with the matching name
    */
    private static Goal findGoalByName(ArrayList<Goal> goals, String name){
        for (Goal g : goals){
            if (g.getName().equals(name)) return g;
        }
        return null;
    }
}
