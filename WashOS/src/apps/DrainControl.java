package apps;

import OS.PCB;
import OS.Process;
import OS.States;

import java.util.concurrent.*;

import IO.IOController;

public class DrainControl extends Process {

	public DrainControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			this.getPcb().setStartTime(System.currentTimeMillis());
			sem.acquire();
			IOController.print("Console semaphore down");
			IOController.print("Process Started");
			this.getPcb().setWaitingTime(System.currentTimeMillis()-this.getPcb().getStartWaitingTime());
//			while (Thread.currentThread().isAlive()) {
				IOController.print("Water is draining out of drum ...");
//			}
			Thread.currentThread().join(intensityInterval);
			this.getPcb().setProcessState(States.TERMINATED);
			this.getPcb().setEndTime(System.currentTimeMillis()-this.getPcb().getStartTime());
			IOController.print("Process Ended");
			IOController.print("Console semaphore up");
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
