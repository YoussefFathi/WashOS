package apps;

import OS.PCB;
import OS.Process;

public class WaterPump extends Process {

	public WaterPump(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Water is pumping into drum ...");

	}

	@Override
	public void run(int temp) {
		// TODO Auto-generated method stub

	}

}
