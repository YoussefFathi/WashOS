package IO;

import java.util.Scanner;

import OS.MainOS;

public class IOController {
	
	public IOController() {
		System.out.println("Please input washing Program");
		String program = readInputFromUser();
		System.out.println("Please input washing Program");
		String intensity = readInputFromUser();
//		MainOS os = new MainOS(program,intensity);
	}
	public static String readInputFromUser() {
		Scanner c = new Scanner(System.in);
		return c.nextLine();
	}
	public static void print(String message) {
		System.out.println(message);
	}
	public static void main(String[] args) {
		IOController IO = new IOController();
	}
}
