import java.util.ArrayList;

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
}
