package OS;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public abstract class Process implements Runnable {
	private PCB pcb;
	private ArrayList threads = new ArrayList<Thread>();
	public static Semaphore sem = new Semaphore(1);
	protected int intensityInterval;
	abstract public void run();
	public void setIntensityInterval(String intensity) {
		switch(intensity) {
		case "High":intensityInterval = 1000;break;
		case "Medium":intensityInterval = 800;break;
		case "Low":intensityInterval = 600;break;
		}
	}
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

	public void block() {
		try {
			sem.acquire();
			pcb.setProcessState(States.BLOCKED);
			sem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void revive() {
//		System.out.println("REVIVE");
		run();
	}

	public void deleteThread(Thread t) {
		threads.remove(t);
	}

	// abstract public void run(int temp) ;
}
