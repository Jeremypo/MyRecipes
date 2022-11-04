import java.util.ArrayList;

public class WorkoutTemplate {
	private String templateName;
	private ArrayList<Exercise> exercises;
	
	private final String DEFAULT_NAME = "Template";
	
	//constructors
	public WorkoutTemplate(String name) {
		setName(name);
	}
	public WorkoutTemplate(){
		setName(DEFAULT_NAME);
	}
	
	//setters
	public void setName(String name){	
		this.templateName = (name.equals("")) ? DEFAULT_NAME : name;
	}
	
	//adjusters
	public void addExercise() {
		Exercise addExercise = new Exercise();
		exercises.add(addExercise);
	}
	public void addExercise(String name) {
		Exercise addExercise = new Exercise(name);
		exercises.add(addExercise);
	}
	public void removeExercise() {
		if(exercises.size() > 0)
			exercises.remove(exercises.size() - 1);
	}
	
	//getters
	public String getName() {
		return templateName;
	}
	
}
