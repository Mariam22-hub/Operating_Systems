//package priority;
//
//import java.util.PriorityQueue;
//import java.util.Scanner;
//
//public class Scheduler {
//	
//	;
//	//initial queue - sorted according to the arrival times
//	public static PriorityQueue<Process> queueD;
//	
//	//second queue - sorted according to the priorities
////	public static PriorityQueue<Process> queueQ = new PriorityQueue<>(new PriorityComparator());
//	
//	//queue for the finished processes
//	public static PriorityQueue<Process> queueF = new PriorityQueue<>(new arrivalTimeComparator());
//	
//	public static int totalWaitTime = 0;
//	public static int currentTime = 0;
//	public static int maxTime = 20;
//	
//	//inputting processes into the queueD
//	public static PriorityQueue<Process> input_processes(int num) {
//		
//		PriorityQueue<Process> queue = new PriorityQueue<>(new arrivalTimeComparator());
//		
//		System.out.println("Please enter the following information for each process: \n");
//		
//		for (int i = 0; i<num; i++) {
//			
//			System.out.println("Process " + (i+1) + ":\n");
//			Scanner scanner = new Scanner(System.in);
//			
//			int bursts;
//			String name;
//			int priority;
//			int arrivalTime;
//			
//			System.out.println("Name: ");
//			name = scanner.nextLine();
//			
//			System.out.println("Arrival Time: ");
//			arrivalTime = scanner.nextInt();
//			
//			System.out.println("Priority: ");
//			priority = scanner.nextInt();
//			
//			System.out.println("Burst Time: ");
//			bursts = scanner.nextInt();
//			
//			
//			Process process = new Process(name, bursts, priority, arrivalTime);
//			
//			queue.add(process);
//
//		}
//		return queue;
//	}
//	
//
//	
//	
//	public static void calculatingWaitingTime(int waitTime, Process process) {
//		waitTime = currentTime - process.arrivalTime;
//		process.waitingTime = waitTime;
//		
//		totalWaitTime += waitTime;
//	}
//	
//	// initial processing - until exceeding the arrival times
//	public static void runProcess(PriorityQueue<Process> queue, int currentTime) {
//		
//		PriorityQueue<Process> queueQ = new PriorityQueue<>(new PriorityComparator());
//		Process process = queue.poll();
////		int waitTime = currentTime - process.arrivalTime;
////		totalWaitTime += waitTime;
////		
////		process.waitingTime = waitTime;
//		process.burstTime --;
//		
//		System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
//		currentTime ++;
//		
//		while (true) {
//			
////			System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
//			
//			//if the process still has execution bursts left -> check if the current time is the arrival time of another process
//			if (process.burstTime > 0) {
//
//				
//				if (queue.peek().arrivalTime == currentTime) {
//					
//					//if the next process has a lower priority than the already running process	
//					if (queue.peek().priority > process.priority) {
//						
//						
//						Process p = queue.poll();
//						queueQ.add(p);
//						
////						process = min(process, queueQ.peek(), queue, queueQ);
//						
//						System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
//						currentTime += 1;
//						process.burstTime--;
//												
//					}
//					
//					// if it does, override the process variable with the new process
//					else if (queue.peek().priority < process.priority) {
//						
//						queueQ.add(process);
//						process = queue.poll();
////						process = min(queue.peek(), queueQ.peek(), queue, queueQ);
//						
////						queueQ.add(process);
////						process = queue.poll();
//						
//						System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
//						currentTime += 1;
//						process.burstTime--;
//
//						
//					}
//				}
//				else {
//					System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
//					currentTime += 1;
//					process.burstTime--;
//				}
//			}
//			else if (process.burstTime == 0) {
//				
//				process.completionTime = currentTime;
//				queueF.add(process);
//				
//				process = min(queue.peek(), queueQ.peek(), queue, queueQ);
//		
//				System.out.println("Process " + process.name + " is running at time " + currentTime + " unit\n");
////				currentTime += 1;
//				process.burstTime--;
//				
//			}
//			
//			if (queue.isEmpty() && queueQ.isEmpty()) {
//				break;
//			}
//
//		}
//	}
//	
//	// p1 -> queue
//	// p2 -> queueQ
//	private static Process min( Process p1, Process p2, PriorityQueue<Process> q, PriorityQueue<Process> z) {
//		
//		if (p1.priority < p2.priority) {
//			q.poll();
//			return p1;
//		}
//		
//		z.poll();
//		return p2;
//	}
//
//
//
////
////	public static void main(String[] args) {
////		
////		System.out.println("Please enter the number of processes: ");
////		Scanner scanner = new Scanner(System.in);
////		int n = scanner.nextInt();
////		
////		//inserting the processes into the initial queue
////		queueD = input_processes(n);
////		runProcess(queueD, currentTime);
////			
////
////	}
//}
