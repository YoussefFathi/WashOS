package CPU;

import java.util.ArrayList;

import OS.PCB;
import OS.Process;
import OS.States;

public class Ram {
	private static Process[] processes = new Process[1024];
	public void Ram() {
		for(int i =0;i<processes.length;i++) {
			processes[i]=null;
		}
	}
	public void addProcess(Process process) {
		int ramAddress = 0;
		boolean foundNull=false;
		for(int i =0;i<processes.length;i++) {
			if(processes[i]==null) {
				processes[i]=process;
				ramAddress = i;
				foundNull=true;
				break;
			}
			
		}
		if(!foundNull) {
			HardDisk.addExtra(process);
			return;
		}
//		processes.add(process);
		
		process.getPcb().setRamAddress(ramAddress);
	}

	public Process deleteProcess(PCB pcb) {
		for (int i = 0; i < processes.length; i++) {
			if (processes[i].getPcb().equals(pcb)) {
				Process returned = processes[i];
				processes[i]=null;
				return returned;
			}

		}
		return null;
	}

	public Process getProcess(PCB pcb) {
		for (int i = 0; i < processes.length; i++) {
			if (processes[i].getPcb().equals(pcb)) {
				return processes[i];
			}

		}
		return null;
	}

	public void refreshRam() {
		for (int i = 0; i < processes.length; i++) {
			if (processes[i].getPcb().getProcessState().equals(States.TERMINATED)) {
				processes[i]=null;
			}

		}
	}

	public Process[] getProcesses() {
		return processes;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
