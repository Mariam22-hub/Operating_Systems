package priority;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Process> {

	@Override
    public int compare(Process a, Process b) {
        if (a.priority < b.priority) return -1;
        else if (a.priority == b.priority) return 0;
        else return 1;
    }
	
}
