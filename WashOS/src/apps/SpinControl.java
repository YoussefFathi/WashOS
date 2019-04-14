package apps;

import IO.IOController;
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
			this.getPcb().setStartTime(System.currentTimeMillis());
			sem.acquire();
			IOController.print("Console semaphore down");
			IOController.print("Process Started");
			this.getPcb().setWaitingTime(System.currentTimeMillis()-this.getPcb().getStartWaitingTime());
			IOController.print("Drum is spinning ...");
			Thread.currentThread().join(intensityInterval);
			this.getPcb().setProcessState(States.TERMINATED);
			this.getPcb().setEndTime(System.currentTimeMillis()-this.getPcb().getStartTime());
			IOController.print("Process Ended");
			IOController.print("Console semaphore up");
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
