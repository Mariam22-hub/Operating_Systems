package priority;

import java.util.Comparator;

public class arrivalTimeComparator implements Comparator<Process> {

    public int compare(Process a, Process b) {
        
    	if (a.arrivalTime < b.arrivalTime) 
    		return -1;
        
    	else if (a.arrivalTime == b.arrivalTime) 
    		return 0;
        
    	else 
    		return 1;
    }
	
}
