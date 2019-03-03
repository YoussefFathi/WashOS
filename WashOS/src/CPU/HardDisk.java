package CPU;

import java.util.ArrayList;
import java.util.Arrays;

import OS.PCB;
import OS.Process;
import OS.States;
import apps.DrainControl;
import apps.SpinControl;
import apps.TemperatureControl;
import apps.WaterPump;

public class HardDisk {
	private static ArrayList<Process> apps = new ArrayList<Process>();

	public HardDisk() {
		apps.add(new TemperatureControl(new PCB("1", States.CREATED, 0, 0)));
		apps.add(new SpinControl(new PCB("2", States.CREATED, 0, 0)));
		apps.add(new DrainControl(new PCB("3", States.CREATED, 0, 0)));
		apps.add(new WaterPump(new PCB("4", States.CREATED, 0, 0)));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Process> getApps() {
		return apps;
	}

	public void setApps(ArrayList<Process> apps) {
		this.apps = apps;
	}

}
