package priority;



public class Process {
	
	public int arrivalTime;
	public int burstTime;
	public int burstTimeOriginal;
	public int priority;
	public String name;
	
	public int waitingTime;
	public int completionTime;
	public int turnAroundTime;
	
	
	public Process (String name, int burst, int priority, int arrival) {
		this.arrivalTime = arrival;
		this.burstTime = burst;
		this.burstTimeOriginal = burst;
		this.name = name;
		this.priority = priority;
	}
	
	//for starvation problem
	//decreasing priority number --> higher priority
	public void decrementPriority() {
		if (this.priority > 1) {
			this.priority--;
		}
	}
	
	
}
