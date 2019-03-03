package CPU;

import java.util.ArrayList;

import apps.DrainControl;
import apps.SpinControl;
import apps.TemperatureControl;
import apps.WaterPump;
import OS.PCB;
import OS.Process;
import OS.States;

public class Cpu  {
	private HardDisk hardDisk = new HardDisk();
	private Ram ram = new Ram();
	
	public void addToRam(Process p) {
		ram.addProcess(p);
	}
	
	public void dispatchToProcessor(PCB pcb) {
		Process app = ram.deleteProcess(pcb);
		app.getPcb().setProcessState(States.RUNNING);
		if (app instanceof TemperatureControl) {
			((TemperatureControl) app).run(32);
		} else if (app instanceof SpinControl) {
			((SpinControl) app).run();
		} else if (app instanceof DrainControl) {
			((DrainControl) app).run();
		} else if (app instanceof WaterPump) {
			((WaterPump) app).run();
		}
	}
	public void setDryPriotrities() {
		// TODO Auto-generated method stub

	}

	public void setRinsePriorities() {
		// TODO Auto-generated method stub

	}

	public void setWashPriorities() {
		for(int i=0;i<ram.getApps().size();i++) {
			if (ram.getApps().get(i) instanceof TemperatureControl) {
				ram.getApps().get(i).getPcb().setPriority(3);
			} else if (ram.getApps().get(i) instanceof SpinControl) {
				ram.getApps().get(i).getPcb().setPriority(2);
			} else if (ram.getApps().get(i) instanceof DrainControl) {
				ram.getApps().get(i).getPcb().setPriority(1);
			} else if (ram.getApps().get(i) instanceof WaterPump) {
				ram.getApps().get(i).getPcb().setPriority(3);
			}
		}

	}
	public ArrayList getWashAppsFromHardDisk() {
		return hardDisk.getApps();

	}
}
