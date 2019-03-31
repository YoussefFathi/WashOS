package CPU;

import java.util.ArrayList;

import OS.PCB;
import OS.Process;
import OS.States;

public class Ram {
	private static ArrayList<Process> processes = new ArrayList<Process>();

	public void addProcess(Process process) {
		processes.add(process);
		process.getPcb().setRamAddress(processes.indexOf(process));
	}

	public Process deleteProcess(PCB pcb) {
		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).getPcb().equals(pcb)) {
				return processes.remove(i);
			}

		}
		return null;
	}

	public Process getProcess(PCB pcb) {
		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).getPcb().equals(pcb)) {
				return processes.get(i);
			}

		}
		return null;
	}

	public void refreshRam() {
		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).getPcb().getProcessState().equals(States.TERMINATED)) {
				processes.remove(i);
			}

		}
	}

	public ArrayList<Process> getProcesses() {
		return processes;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
