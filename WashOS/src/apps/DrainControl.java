package apps;

import OS.PCB;
import OS.Process;

public class DrainControl extends Process {

	public DrainControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Water is draining out of drum ...");

	}

	@Override
	public void run(int temp) {
		// TODO Auto-generated method stub

	}

}
