/** 
* 
*/
public class GoalLog{
	private Goal goal;
	private String date;

	/** ... */
	public GoalLog(Goal goal, String date) {
		this.goal = goal;
		this.date = date;
	}

	/** ... */
	public Goal getGoal() {
		return goal;
	}

	/** ... */
	public String getDate() {
		return date;
	}

	/** ... */
	public String toString() {
		return date + ": " + goal.getName();
	}
}
