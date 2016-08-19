package grail.runnables;

import grail.helpers.ClearanceManager;
import util.annotations.Tags;

@Tags({"ProceedAllCommand"})
public class ProceedAllCommand implements Runnable {
	private ClearanceManager manager;
	
	public ProceedAllCommand(ClearanceManager manager) {
		this.manager = manager;
	}

	public void run() {
		this.manager.proceedAll();
	}

}
