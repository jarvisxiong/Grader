package old.echoerAndIM;
import util.misc.Console;
public class AnInputEchoer implements InputEchoer {
	static String QUIT_COMMAND = "quit";	
	void prompt() {
		System.out.println("Please enter an input line, or " + QUIT_COMMAND + " for leaving the session");
	}
	boolean isQuit(String input) {
		return input.equals(QUIT_COMMAND);
	}
	void processInput(String theNextInput) {
		System.out.println("Input:" + theNextInput);
	}
	public void doInput() {
		for (;;) {
			prompt();
			String nextInput = Console.readString();
			if (isQuit(nextInput)) { 
				break;
			}
			processInput(nextInput);			
		}
	}
}
