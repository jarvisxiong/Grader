package grail.runnables;

import grail.interfaces.UndoableRunnable;
import util.annotations.Tags;

@Tags({"RedoCommand"})
public class RedoCommand implements Runnable {
	private UndoableRunnable command;
	
	public RedoCommand(UndoableRunnable command) {
		this.command = command;
	}

	public void run() {
		this.command.redo();
	}


}
