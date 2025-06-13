import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors; // Make sure this import is present


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
	public void logGoalCompletion(Goal goal, String date) {
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
		// Map to store the count of completed goals for each date
		Map<LocalDate, Integer> goalsCompletedPerDay = new HashMap<>();

		for (GoalLog log : logs) {
			try {
				LocalDate logDate = LocalDate.parse(log.getDate());
				goalsCompletedPerDay.put(logDate, goalsCompletedPerDay.getOrDefault(logDate, 0) + 1);
			} catch (DateTimeParseException e) {
				System.err.println("Warning: Could not parse date '" + log.getDate() + "' for streak calculation. Please ensure dates are in YYYY-MM-DD format.");
				// Continue processing other logs, but this specific log won't contribute to the streak
			}
		}

		// Get a sorted list of unique dates where goals were completed
		List<LocalDate> sortedUniqueDates = goalsCompletedPerDay.keySet().stream()
												.sorted()
												.collect(Collectors.toList());

		// A streak of 30 days requires at least 30 unique dates
		if (sortedUniqueDates.size() < 30) {
			return false;
		}

		int currentStreak = 0;
		// Iterate through the sorted unique dates to find a consecutive streak
		for (int i = 0; i < sortedUniqueDates.size(); i++) {
			LocalDate currentDate = sortedUniqueDates.get(i);

			// Check if at least 2 goals were completed on the current date
			if (goalsCompletedPerDay.getOrDefault(currentDate, 0) >= 2) {
				if (currentStreak == 0) {
					// Start a new streak
					currentStreak = 1;
				} else {
					// Check if the current date is consecutive to the previous date
					LocalDate previousDate = sortedUniqueDates.get(i - 1);
					if (currentDate.minusDays(1).equals(previousDate)) {
						currentStreak++;
					} else {
						// Streak broken, reset
						currentStreak = 1; // Start a new streak from this date
					}
				}
				if (currentStreak >= 30) {
					return true; // Found a 30-day streak
				}
			} else {
				// Less than 2 goals were completed, streak is broken
				currentStreak = 0;
			}
		}

		return false; // No 30-day streak found
	}
}
