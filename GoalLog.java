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

	/** 
 	* Gets the goal related to this log.
     	* @return the logged goal
  	*/
	public Goal getGoal() {
		return goal;
	}

	/** 
 	* Gets the date the goal was completed.
     	* @return date of completion
  	*/
	public String getDate() {
		return date;
	}

	/** 
 	* Returns a string showing the date and goal name.
     	* @return formatted log string
  	*/
	public String toString() {
		return date + ": " + goal.getName();
	}
}
