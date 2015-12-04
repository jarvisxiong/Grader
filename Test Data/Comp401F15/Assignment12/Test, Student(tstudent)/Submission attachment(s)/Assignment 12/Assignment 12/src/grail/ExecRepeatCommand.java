package grail;

import util.annotations.Tags;

@Tags({"Repeat"})
public class ExecRepeatCommand implements Runnable{
	int reps;
	Runnable theCommand;
	
	public ExecRepeatCommand (int repetitions, Runnable command) {
		reps = repetitions;
		theCommand = command;
	}
	
	public void run() {
		for (int x = 0; x < reps; x++) {
			theCommand.run();
		}
	}
	
}
