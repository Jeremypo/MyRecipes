import java.util.ArrayList;

public class Exercise {
	private ArrayList<Set> sets;
	private String exerciseName;
	
	private final String DEFAULT_NAME = "Exercise";
	
	//constructors
	public Exercise(String name) {
		setName(name);
	}
	public Exercise() {
		setName(DEFAULT_NAME);
	}
	
	//setters
	public void setName(String name){	
		this.exerciseName = (name.equals("")) ? DEFAULT_NAME : name;
	}
	
	//adjusters
	public void addSet() {
		Set addSet = new Set();
		sets.add(addSet);
	}
	public void removeSet() {
		if(sets.size() > 0)
			sets.remove(sets.size() - 1);
	}
	
	//getters
	public String getName() {
		return exerciseName;
	}
	public int setCount() {
		return sets.size();
	}
	
}
