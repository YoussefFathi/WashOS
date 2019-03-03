package apps;

import OS.PCB;
import OS.Process;

public class TemperatureControl extends Process {

	public TemperatureControl(PCB pcb) {
		super(pcb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(int temp) {
		System.out.println("Temperature is set to "+temp);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
