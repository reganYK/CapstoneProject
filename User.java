import java.util.*;

/**
* Represents a user of the EcoPlanner.
* Stores the user’s goals, logs, and provides streak tracking logic.
*/
public class User {
	/** the user's username */
	private String username;
	/** the list of all goals that belong to the user */
 	private ArrayList<Goal> goals;
	/** the log of completed goals */
	private ArrayList<GoalLog> logs;

	/**
 	* Constructs a User with a given username.
     	* @param username the name of the user
  	*/
	public User(String username) {
		this.username = username;
		this.goals = new ArrayList<>();
		this.logs = new ArrayList<>();
	}

	/**
 	* Adds a goal to a user's list of goals.
     	* @param goal that is going to be added
  	*/
	public void addGoal(Goal goal) {
		goals.add(goal);
	}

	/**
	* Logs the completion of a goal for a specific date.
        * @param goal the completed goal
	* @param date it was completed
  	*/
	public void logGoalCompleteion(Goal goal, String date) {
		logs.add(new GoalLog(goal, date));
	}

	/**
     	* @return list of goals
  	*/
	public ArrayList<Goal> getGoals() {
		return goals;
	}

	/**
     	* @return list of goal logs
  	*/
	public ArrayList<GoalLog> getLogs() {
		return logs;
	}

	/**
     	* @return username
  	*/
	public String getUsername() {
		return username;
	}

	/**
 	* Counts how many goals were completed on a given date.
     	* @param date the date to check
  	* @param number of goals completed on that date
   	*/
	public int getGoalsCompletedOnDate(String date) {
		int count = 0;
		for(GoalLog log : logs) {
			if(log.getDate().equals(date)) {
				count ++;
			}
		}
		return count;
	}

	/**
 * Checks if the user has completed at least 2 goals per day for 30 consecutive days.
 * Used to determine if a tree should be planted under the user's name.
 * This version does not use a HashMap — only basic structures.
 * 
 * @return true if 30-day streak is reached, false otherwise
 */
public boolean hasStreak() {
	List<String> allDates = new ArrayList<>();

	// Collect all log dates
	for (GoalLog log : logs) {
		allDates.add(log.getDate());
	}

	// Sort the dates
	Collections.sort(allDates);

	// Remove duplicates and count how many goals were completed per day
	List<String> uniqueDates = new ArrayList<>();
	List<Integer> goalsPerDay = new ArrayList<>();

	for (String date : allDates) {
		if (!uniqueDates.contains(date)) {
			uniqueDates.add(date);
			goalsPerDay.add(1);
		} else {
			int index = uniqueDates.indexOf(date);
			goalsPerDay.set(index, goalsPerDay.get(index) + 1);
		}
	}

	// Check for a streak of 30 consecutive days with at least 2 goals per day
	int streak = 0;
	for (int i = 0; i < uniqueDates.size(); i++) {
		if (goalsPerDay.get(i) >= 2) {
			streak++;
			if (streak == 30) return true;
		} else {
			streak = 0;
		}
	}

	return false;
}
}
