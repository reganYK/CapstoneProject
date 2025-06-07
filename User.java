import java.util.*;

/**
*
*/
public class User {
	private String username;
	private ArrayList<Goal> goals;
	private ArrayList<GoalLog> logs;

	/** ... */
	public User(String username) {
		this.username = username;
		this.goals = new ArrayList<>();
		this.logs = new ArrayList<>();
	}

	/** ... */
	public void addGoal(Goal goal) {
		goals.add(goal);
	}

	/** ... */
	public void logGoalCompleteion(Goal goal, String date) {
		logs.add(new GoalLog(goal, date));
	}

	/** ... */
	public ArrayList<Goal> getGoals() {
		return goals;
	}

	/** ... */
	public ArrayList<GoalLog> getLogs() {
		return logs;
	}

	/** ... */
	public String getUsername() {
		return username;
	}

	/** ... */
	public int getGoalsCompletedOnDate(String date) {
		int count = 0;
		for(GoalLog log : logs) {
			if(log.getDate().equals(date)) {
				count ++;
			}
		}
		return count;
	}

	/** ... */
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
