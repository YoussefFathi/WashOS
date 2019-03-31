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
//		ram.refreshRam();
		Process app = ram.getProcess(pcb);
		app.getPcb().setProcessState(States.RUNNING);
		if (app instanceof TemperatureControl) {
			((TemperatureControl) app).run();
		} else if (app instanceof SpinControl) {
			((SpinControl) app).run();
		} else if (app instanceof DrainControl) {
			((DrainControl) app).run();
		} else if (app instanceof WaterPump) {
			((WaterPump) app).run();
		}
//		ram.refreshRam();
	}
	public void setDryPriotrities() {
		// TODO Auto-generated method stub

	}

	public void setRinsePriorities() {
		// TODO Auto-generated method stub

	}

	public void setWashPriorities() {
		for(int i=0;i<ram.getProcesses().size();i++) {
			if (ram.getProcesses().get(i) instanceof TemperatureControl) {
				ram.getProcesses().get(i).getPcb().setPriority(3);
			} else if (ram.getProcesses().get(i) instanceof SpinControl) {
				ram.getProcesses().get(i).getPcb().setPriority(2);
			} else if (ram.getProcesses().get(i) instanceof DrainControl) {
				ram.getProcesses().get(i).getPcb().setPriority(1);
			} else if (ram.getProcesses().get(i) instanceof WaterPump) {
				ram.getProcesses().get(i).getPcb().setPriority(3);
			}
		}

	}
	public void interrupt(PCB running) {
//		Process app = ram.deleteProcess(running);
		Process app  = ram.getProcess(running);
		app.getPcb().setProcessState(States.BLOCKED);
		if (app instanceof TemperatureControl) {
			((TemperatureControl) app).block();
		} else if (app instanceof SpinControl) {
			((SpinControl) app).block();
		} else if (app instanceof DrainControl) {
			((DrainControl) app).block();
		} else if (app instanceof WaterPump) {
			((WaterPump) app).block();
		}
	}
	public void revive(PCB blocked) {
		Process app = ram.getProcess(blocked);
		app.getPcb().setProcessState(States.RUNNING);
		if (app instanceof TemperatureControl) {
			((TemperatureControl) app).revive();
		} else if (app instanceof SpinControl) {
			((SpinControl) app).revive();
		} else if (app instanceof DrainControl) {
			((DrainControl) app).revive();
		} else if (app instanceof WaterPump) {
			((WaterPump) app).revive();
		}
	}
	public ArrayList getWashAppsFromHardDisk() {
		return hardDisk.getApps();

	}
}
