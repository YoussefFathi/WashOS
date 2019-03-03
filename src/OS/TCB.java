package OS;

import java.util.UUID;

public class TCB {
	private String id = UUID.randomUUID().toString(); 
	private PCB pcb;
	private States threadState;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
