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
import javax.swing.SwingUtilities;

import CPU.Cpu;
import CPU.Ram;
import views.Chart;

import org.jfree.chart.*;
public class MainOS implements ActionListener {
	private String currentProgram;
	private Queue<PCB> ready = new LinkedList<PCB>();
	private Queue<PCB> blocked = new LinkedList<>();
	private volatile boolean interrupted = false;
	private int intensityCount;
	private String intensity;
	Semaphore c = new Semaphore(1);
	private PCB running;
	private long startTime;
	private long endTime;
	private ArrayList<Long> executionTimes = new ArrayList<Long>();
	private ArrayList<Long> waitingTimes = new ArrayList<Long>();

	// private Queue<Process> created = new LinkedList<>();
	private Cpu cpu = new Cpu();

	public MainOS(String program, String intensity,long startTime) {
		initializeButtons();
		this.currentProgram = program;
		this.intensity = intensity;
		this.startTime = startTime;
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
						((Process)app).getPcb().setStartWaitingTime(System.currentTimeMillis());
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
	public void printAnalysis() {
		System.out.println("***********************************");
		ArrayList<Long> turnAroundTimes = new ArrayList<Long>();
		double sumTurn = 0;
		for(int i=0;i<waitingTimes.size();i++) {
			turnAroundTimes.add(waitingTimes.get(i)+executionTimes.get(i));
			sumTurn = sumTurn +waitingTimes.get(i)+executionTimes.get(i);
		}
		SwingUtilities.invokeLater(() -> {
            Chart ex1 = new Chart(executionTimes,"E");
            ex1.setVisible(true);
            Chart ex2 = new Chart(waitingTimes,"R");
            ex2.setVisible(true);
            Chart ex3 = new Chart(turnAroundTimes,"T");
            ex3.setVisible(true);
        });
		double sumExecution = 0;
		for(int i=0;i<executionTimes.size();i++) {
			sumExecution = sumExecution+executionTimes.get(i);
		}
		double sumWaiting = 0;
		for(int i=0;i<waitingTimes.size();i++) {
			sumWaiting = sumWaiting+waitingTimes.get(i);
		}
		double totalCpuTime = System.currentTimeMillis()-startTime;
		double cpuUtil = (sumExecution/totalCpuTime)*100;
		double avgResponse = (sumWaiting/waitingTimes.size());
		double avgTurn = (sumTurn/waitingTimes.size());
		System.out.println("CPU UTILIZATION: "+cpuUtil +"%");
		System.out.println("Average Response Time: "+avgResponse +" ms");
		System.out.println("Average TurnAround Time: "+avgTurn +" ms");
	}
	public Thread shortTermScheduler = new Thread() {
		public void run() {
			while (intensityCount > 0) {
				while (!ready.isEmpty()) {
					if (!interrupted) {
						running = ready.remove();
						// System.out.println(running.getPriority());
						System.out.println("--------------------------------------------");
						cpu.dispatchToProcessor(running);
						executionTimes.add(running.getEndTime());
						waitingTimes.add(running.getWaitingTime());
//						System.out.println(running.getEndTime() + "End");
//						System.out.println(running.getWaitingTime() + "Waiting");
					}
				}
				if (!interrupted) {
					intensityCount--;
					c.release();
					System.out.println("*************************************");
					System.out.println("Round of Processes Finished");
					System.out.println("*************************************");
					longTermScheduler();
				}
			}
			printAnalysis();
		}
	};

	public void handleInterrupt() {
		System.out.println("Process Interrupted");
		interrupted = true;
		blocked.add(running);
		System.out.println("Process Interrupted");
		cpu.interrupt(running);
	}

	public void handleRevival() {
		while (!blocked.isEmpty()) {
			PCB blockedPCB = blocked.remove();
			System.out.println("Process Resumed");
			cpu.revive(blockedPCB);
			interrupted = false;
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		MainOS os = new MainOS("wash", "High",start);
//		System.out.println(System.currentTimeMillis()-start);
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
