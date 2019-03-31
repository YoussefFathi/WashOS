package OS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import CPU.Cpu;
import CPU.Ram;

public class MainOS implements ActionListener {
	private String currentProgram;
	private Queue<PCB> ready = new LinkedList<PCB>();
	private Queue<PCB> blocked = new LinkedList<>();
	private volatile boolean interrupted = false;
	private int intensityCount;
	private String intensity;
	Semaphore c = new Semaphore(1);
	private PCB running;
	// private Queue<Process> created = new LinkedList<>();
	private Cpu cpu = new Cpu();

	public MainOS(String program, String intensity) {
		initializeButtons();
		this.currentProgram = program;
		this.intensity = intensity;
		this.setIntensityCount(intensity);
		this.longTermScheduler();
		// this.setPriorities();
		// Collections.sort(ready, new ComparePriorities());
		shortTermScheduler.start();
	}

	public void initializeButtons() {
		JFrame all = new JFrame();
		JPanel content = new JPanel();
		JButton pause = new JButton("Pause");
		JButton resume = new JButton("Resume");
		pause.setActionCommand("Pause");
		pause.addActionListener(this);
		resume.setActionCommand("Resume");
		resume.addActionListener(this);
		content.add(pause);
		content.add(resume);
		all.add(content);
		all.setSize(200, 300);
		all.setVisible(true);
	}

	public void setIntensityCount(String intensity) {
		switch (intensity) {
		case "High":
			intensityCount = 2;
			break;
		default:
			intensityCount = 1;
			break;
		}
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
	public void longTermScheduler() {
		try {

			if (intensityCount > 0) {
				c.acquire();
				if (currentProgram.equals("wash")) {
					cpu.getWashAppsFromHardDisk().forEach((app) -> {
						((Process) app).getPcb().setProcessState(States.READY);
						((Process) app).setIntensityInterval(intensity);
						cpu.addToRam((Process) app);
						ready.add(((Process) app).getPcb());
					});
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(ready);

	}

	public Thread shortTermScheduler = new Thread() {
		public void run() {
			while (intensityCount > 0) {
				while (!ready.isEmpty()) {
					if (!interrupted) {
						running = ready.remove();
						// System.out.println(running.getPriority());
						cpu.dispatchToProcessor(running);
						
					}
					
				}
				if (!interrupted) {
					intensityCount--;
					c.release();
					longTermScheduler();
				}
			}

		}
	};

	public void handleInterrupt() {
		interrupted = true;
		blocked.add(running);
		cpu.interrupt(running);
	}

	public void handleRevival() {
		while (!blocked.isEmpty()) {
			PCB blockedPCB = blocked.remove();
			cpu.revive(blockedPCB);
			interrupted = false;
		}
	}

	public static void main(String[] args) {
		MainOS os = new MainOS("wash", "High");
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Pause")) {
			this.handleInterrupt();
		} else if (e.getActionCommand().equals("Resume")) {
			this.handleRevival();
		}

	}

}
