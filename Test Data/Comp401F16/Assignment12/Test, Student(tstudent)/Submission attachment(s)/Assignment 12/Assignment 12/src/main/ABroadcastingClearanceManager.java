package main;

import util.annotations.ComponentWidth;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.NO_PATTERN)
public class ABroadcastingClearanceManager extends AClearanceManager implements BroadcastingClearanceManager {

	@Row(1)
	@ComponentWidth(100)
	public synchronized void proceedAll() {
		clearance = true;
		notifyAll();		
	}
}
