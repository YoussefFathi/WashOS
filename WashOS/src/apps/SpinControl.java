package apps;

import OS.PCB;
import OS.Process;

public class SpinControl extends Process {

	public SpinControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Drum is spinning ...");
	}

	@Override
	public void run(int temp) {
		// TODO Auto-generated method stub

	}

}
