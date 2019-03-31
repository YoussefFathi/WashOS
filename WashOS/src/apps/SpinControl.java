package apps;

import OS.PCB;
import OS.Process;
import OS.States;

public class SpinControl extends Process {

	public SpinControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			sem.acquire();
			System.out.println("Drum is spinning ...");
			Thread.currentThread().join(intensityInterval);
			this.getPcb().setProcessState(States.TERMINATED);
			sem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Moshkela hena");
			e.printStackTrace();
			sem.release();
		}

	}

//	@Override
//	public void run(int temp) {
//		// TODO Auto-generated method stub

//	}

}
