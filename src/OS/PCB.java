package OS;

public class PCB {
	private String processID;
	private States processState;
	private int priority;
	private int hardDiskAddress;
	private int ramAddress;

	public PCB(String processID, States processState, int priority, int address) {
		this.processID = processID;
		this.processState = processState;
		this.priority = priority;
		this.hardDiskAddress = address;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public States getProcessState() {
		return processState;
	}

	public void setProcessState(States processState) {
		this.processState = processState;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int gethardDiskAddress() {
		return hardDiskAddress;
	}

	public void sethardDiskAddress(int memoryAddress) {
		this.hardDiskAddress = memoryAddress;
	}

	public int getRamAddress() {
		return ramAddress;
	}

	public void setRamAddress(int ramAddress) {
		this.ramAddress = ramAddress;
	}

}
