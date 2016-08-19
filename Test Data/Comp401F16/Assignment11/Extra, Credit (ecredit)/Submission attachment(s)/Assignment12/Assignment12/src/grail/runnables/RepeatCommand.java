package grail.runnables;

import util.annotations.Tags;

@Tags({"RepeatCommand"})
public class RepeatCommand implements Runnable {
	private int timesToRun;
	private Runnable currentCommand;
	
	public RepeatCommand(int timeToRun, Runnable command ) {
		this.timesToRun = timeToRun;
		this.currentCommand = command;
	}

	public void run() {
		for (int i =0; i < timesToRun; i++) {
			this.currentCommand.run();
		}

	}

}
