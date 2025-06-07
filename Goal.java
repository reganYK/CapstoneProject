/**
*
*
*/
public class Goal {
	private String name;
	private String description;

	/**... */
	public Goal (String name, String description){
		this.name = name;
		this.description = description;
	}

	/** ... */
	public String getName() {
		return name;
	}

	/** ... */
	public String getDescription() {
		return description;
	}

	/** ... */
	public void setName() {
		this.name = name;
	}

	/** ... */
	public void setDescription() {
		this.description = description;
	}

	/** ... */
	public String toString() {
		return name + ": " + description;
	}
}


	
