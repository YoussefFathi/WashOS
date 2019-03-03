package OS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import CPU.Cpu;
import CPU.Ram;

public class MainOS {
	private String currentProgram;
	private ArrayList<PCB> ready = new ArrayList<PCB>();
	private Queue<PCB> blocked = new LinkedList<>();
	private Queue<PCB> parallelRunning = new LinkedList<>();
	private PCB running;
	// private Queue<Process> created = new LinkedList<>();
	private Cpu cpu = new Cpu();

	public MainOS(String program) {
		this.currentProgram = program;
		this.dispatchToReady();
		this.setPriorities();
		Collections.sort(ready, new ComparePriorities());

		scheduler.start();
	}

	public void setPriorities() {
		switch (currentProgram) {
		case "wash":
			cpu.setWashPriorities();
			break;
		case "rinse":
			cpu.setRinsePriorities();
			break;
		case "dry":
			cpu.setDryPriotrities();
			break;
		}
	}

	@SuppressWarnings("unchecked")
	public void dispatchToReady() {
		if (currentProgram.equals("wash")) {
			cpu.getWashAppsFromHardDisk().forEach((app) -> {
				((Process) app).getPcb().setProcessState(States.READY);
				cpu.addToRam((Process) app);
				ready.add(((Process) app).getPcb());
			});
		}

		// System.out.println(ready);
	}

	public Thread scheduler = new Thread() {
		public void run() {

			while (!ready.isEmpty()) {
				running = ready.remove(ready.size() - 1);
				System.out.println(running.getPriority());
				cpu.dispatchToProcessor(running);
			}
		}
	};

	public static void main(String[] args) {
		MainOS os = new MainOS("wash");
	}

}
