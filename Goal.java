/**
* Represents a general goal with a name and description.
* A base class for both predefined and custom goals
*/
public class Goal {
	/** the title of the goal */
	private String name;

	/** the explanation of the goal */
	private String description;

	/** 
 	* Constructs a Goal object with a name and description.
     	* @param name the name of the goal
     	* @param description the explanation or details of the goal
  	*/
	public Goal (String name, String description){
		this.name = name;
		this.description = description;
	}

	/** 
 	* Returns the name of the goal.
     	* @return goal name
  	*/
	public String getName() {
		return name;
	}

	/** 
 	* Returns the description of the goal.
     	* @return goal description
  	*/
	public String getDescription() {
		return description;
	}

	/** 
 	* Updates the name of the goal.
     	* @param name new name for the goal
  	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
 	* Updates the description of the goal.
     	* @param description new description for the goal
  	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/** 
 	* Returns a string representation of the goal.
     	* @return formatted string with name and description 
      	*/
	public String toString() {
		return name + ": " + description;
	}
}


	
