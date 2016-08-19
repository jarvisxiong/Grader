package grail.helpers;

import grail.helpers.ClearanceManager;
import util.annotations.ComponentWidth;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.NO_PATTERN)
public class BroadcastingClearanceManager implements ClearanceManager {
	private final int componentWidth = 100;
	
	public BroadcastingClearanceManager() {
	}

    @Row(0)
    @ComponentWidth(componentWidth)
	public synchronized void proceed() {
		notify();
	}

    @Row(1)
    @ComponentWidth(componentWidth)
	public synchronized void proceedAll() {
		notifyAll();
		
	}

	public synchronized void waitForProceed() {
		try {
			wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
