package grail.runnables;

import grail.interfaces.UndoableRunnable;
import util.annotations.Tags;

@Tags({"UndoCommand"})
public class UndoCommand implements Runnable{
	private UndoableRunnable command;
	
	public UndoCommand(UndoableRunnable command) {
		this.command = command;
	}

	public void run() {
		this.command.undo();
	}



}
