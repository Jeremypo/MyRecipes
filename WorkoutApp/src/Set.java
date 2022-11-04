public class Set {
	private double weight;
	private int reps;
	
	//constructors
	public Set(double weight, int reps) {
		setWeight(weight);
		setReps(reps);
	}
	public Set() {
		weight = 0.0;
		reps = 0;
	}
	
	//setters
	public void setWeight(double weight) {
		this.weight = (weight < 0.0) ? 0.0 : weight;
	}
	public void setReps(int reps) {
		this.reps = (reps < 0) ? 0 : reps;
	}
	
	//adjusters
	public void adjustWeight(double adjust) {
		setWeight(this.weight + adjust);
	}
	public void adjustReps(int adjust) {
		setReps(this.reps + adjust);
	}
	
	//getters
	public double getWeight() {
		return weight;
	}
	public int getReps() {
		return reps;
	}
}
