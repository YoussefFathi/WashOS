package OS;

import java.util.ArrayList;

public abstract class Process {
	private PCB pcb;
	private ArrayList threads = new ArrayList<Thread>();

	abstract public void run();

	public Process(PCB pcb) {
		this.pcb = pcb;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public PCB getPcb() {
		return pcb;
	}

	public void setPcb(PCB pcb) {
		this.pcb = pcb;
	}

	public void addThread(Thread thread) {
		threads.add(thread);
	}

	public void deleteThread(Thread t) {
		threads.remove(t);
	}

	abstract public void run(int temp) ;
}
