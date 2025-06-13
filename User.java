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
     	* @return true if 30-day streak is reached, false otherwise
  	*/
	public boolean hasStreak() {
		HashMap>String, Integer> dailyCount new HashMap<>();
		for(GoalLog log : logs) {
			dailyCount.put(log.getDate(),dailyCount.getOrDefault(log.getData(), 0) + 1);
		}

		ArrayList<String> sortedDates = new ArrayList<>(dailyCount.keySet());
		Collections.sort(sortedDates);

		int streak = 0;
		for(String date : sortedDates) {
			if(dailyCount.get(date) >= 2) {
				streak++;
				if (streak == 30) return true;
			} else {
				streak = 0;
			}
		}
		return false;
	}
}
