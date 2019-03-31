package apps;

import OS.PCB;
import OS.Process;
import OS.States;

import java.util.concurrent.*;

public class DrainControl extends Process {

	public DrainControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			sem.acquire();
//			while (Thread.currentThread().isAlive()) {
				System.out.println("Water is draining out of drum ...");
//			}
			Thread.currentThread().join(intensityInterval);
			this.getPcb().setProcessState(States.TERMINATED);
			sem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Override
	// public void run(int temp) {
	// // TODO Auto-generated method stub
	//
	// }

}
