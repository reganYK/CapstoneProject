/** 
* Represents a log entry that tracks when a specific goal was completed.
*/
public class GoalLog{
	/** the goal completed by the user */
	private Goal goal;
	/** the date the goal was completed (YYYY-MM-DD) */
	private String date;

	/** 
 	* Constructs a GoalLog entry with a goal and completion date.
     	* @param goal the goal that was completed
     	* @param date the date it was completed (format: YYYY-MM-DD)
  	*/
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
