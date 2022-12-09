package priority;

import java.util.Formatter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Scheduler2 {
	
	;
	//initial queue - sorted according to the arrival times
	public static PriorityQueue<Process> queueD;
	
	//second queue - sorted according to the priorities
	public static PriorityQueue<Process> queueQ = new PriorityQueue<>(new PriorityComparator());
	
	//queue for the finished processes
	public static PriorityQueue<Process> queueF = new PriorityQueue<>(new arrivalTimeComparator());

	
	//inputting processes into the queueD
	public static PriorityQueue<Process> input_processes(int num) {
		
		PriorityQueue<Process> queue = new PriorityQueue<>(new arrivalTimeComparator());
		
		System.out.println("Please enter the following information for each process: \n");
		
		for (int i = 0; i<num; i++) {
			
			System.out.println("Process " + (i+1) + ":\n");
			Scanner scanner = new Scanner(System.in);
			
			int bursts;
			String name;
			int priority;
			int arrivalTime;
			
			System.out.println("Name: ");
			name = scanner.nextLine();
			
			System.out.println("Arrival Time: ");
			arrivalTime = scanner.nextInt();
			
			System.out.println("Priority: ");
			priority = scanner.nextInt();
			
			System.out.println("Burst Time: ");
			bursts = scanner.nextInt();
			
			
			Process process = new Process(name, bursts, priority, arrivalTime);
			
			queue.add(process);

		}
		return queue;
	}
	


	public static int runProcess(PriorityQueue<Process> queue, int currentTime) {
		
		Process process = queue.poll();
		
		//looping through the ready queue
		while (true) {
			
			//special case
			if (queue.isEmpty()) {
				
				while (process.burstTime != 0) {
					System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
					process.burstTime--;
					currentTime++;
				}
				updateProcess(process, currentTime);
				
				if (!queueF.contains(process)) {
					queueF.add(process);
				}
					
				break;
			}
			
			System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
			process.burstTime--;
			currentTime++;
			
			if (queue.peek().arrivalTime == currentTime && process.priority > queue.peek().priority) {
				queueQ.add(process);
				
				process = queue.poll();
			}
			else if (queue.peek().arrivalTime == currentTime && process.priority < queue.peek().priority) {
				Process p = queue.poll();
				queueQ.add(p);
			}
			
			if (process.burstTime == 0) {
				updateProcess(process, currentTime);
				
				queueF.add(process);
				
				if (!queue.isEmpty() && !queueQ.isEmpty()) {
					process = min(queue.peek(), queueQ.peek(), queue, queueQ);
				}
				
				
			}
			
			
		}
		
		return currentTime;
		
	}
	
	//looping through the waiting queue
	public static void runPriority(PriorityQueue<Process> queue, int currentTime) {
		Process process = queue.poll();
		
		while (true) {

			System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
			process.burstTime--;
			currentTime++;
			
			if (process.burstTime == 0) {
				updateProcess(process, currentTime);
				queueF.add(process);
				
				if (!queue.isEmpty()) {
					process = queue.poll();
				}
				else {
					break;
				}
				
				
			}
			
		}
	}
	
	// p1 -> queue
	// p2 -> queueQ
	public static Process min( Process p1, Process p2, PriorityQueue<Process> q, PriorityQueue<Process> z) {
		
		if (p1.priority < p2.priority) {
			q.poll();
			return p1;
		}
		
		z.poll();
		return p2;
	}
	
	
	public static void updateProcess(Process process, int currentTime) {
		process.completionTime = currentTime;
		process.turnAroundTime = process.completionTime - process.arrivalTime;
		process.waitingTime = process.turnAroundTime - process.burstTimeOriginal;
	}
	
	
	

	public static void main(String[] args) {
		
		int currentTime = 0;
		double AVGtotalWaitingTime = 0;
		double AVGtotal_turnAround = 0;
		int size;
		
		System.out.println("Please enter the number of processes: ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
//		inserting the processes into the initial queue
		queueD = input_processes(n);
		
		size = queueD.size();
		
		currentTime = runProcess(queueD, currentTime);
		runPriority(queueQ, currentTime);
		
		//table of information
		Formatter fmt = new Formatter();  
		
		fmt.format("%11s %11s %19s %13s %20s %20s %20s\n", "Process Name", "Bursts", "Arrival Time", "Priority", "Completion Time", "Waiting Time", "TurnAround Time"); 	
		
		while (!queueF.isEmpty()) {
			Process process = queueF.poll();
			
			fmt.format("%6s %14s %16s %16s %17s %21s %18s\n", process.name, process.burstTimeOriginal, process.arrivalTime, process.priority, process.completionTime, process.waitingTime, process.turnAroundTime);
			
			AVGtotalWaitingTime += process.waitingTime;
			AVGtotal_turnAround += process.turnAroundTime;
			
		}
		
		System.out.println(fmt);
		
		System.out.println("------------------------");
		System.out.println("AVG Waitng Time: " + AVGtotalWaitingTime/size + "\n");
		System.out.println("AVG TurnArount Time: " + AVGtotal_turnAround/size);
		
		

	}
}
