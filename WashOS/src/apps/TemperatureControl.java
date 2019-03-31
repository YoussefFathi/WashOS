package apps;

import OS.PCB;
import OS.Process;
import OS.States;

public class TemperatureControl extends Process {

	public TemperatureControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			sem.acquire();
			System.out.println("Temperature is set");
			Thread.currentThread().join(intensityInterval);
			this.getPcb().setProcessState(States.TERMINATED);
			sem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//
//	}

}
