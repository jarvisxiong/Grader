package mp.bean.commands;

import mp.bean.animation.BroadcastingClearanceManager;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"ProceedAllCommand"})

public class ProceedAllCommand implements Runnable {
	
	BroadcastingClearanceManager cManager;

	public ProceedAllCommand(BroadcastingClearanceManager clearanceManager) {
		cManager = clearanceManager;
	}
	
	public void run() {
		cManager.proceedAll();
	}

}