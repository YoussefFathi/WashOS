package CPU;

import java.util.ArrayList;

import OS.PCB;
import OS.Process;

public class Ram {
	private static ArrayList<Process> apps = new ArrayList<Process>();

	public void addProcess(Process process) {
		apps.add(process);
		process.getPcb().setRamAddress(apps.indexOf(process));
	}
	public Process deleteProcess(PCB pcb) {
		 for(int i =0;i<apps.size();i++) {
			 if(apps.get(i).getPcb().equals(pcb)) {
				 return apps.get(i);
			 }
		 
	}
		 return null;
	}
	public ArrayList<Process> getApps(){
		return apps;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
