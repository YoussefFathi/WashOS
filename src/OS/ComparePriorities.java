package OS;

import java.util.Comparator;

 class ComparePriorities implements Comparator<PCB> {


	@Override
	public int compare(PCB arg0, PCB arg1) {
		return arg0.getPriority()-arg1.getPriority();
	}

}
