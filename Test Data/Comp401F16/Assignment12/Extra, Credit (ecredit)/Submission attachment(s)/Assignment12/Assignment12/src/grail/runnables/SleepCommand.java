package grail.runnables;

import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"SleepCommand"})
public class SleepCommand implements Runnable {
	long sleepTime;
	
	public SleepCommand(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public void run() {
		ThreadSupport.sleep(this.sleepTime);
	}

}
