package mp.bean.commands;

import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"SleepCommand"})

public class SleepCommand implements Runnable {
	
	long time;

	public SleepCommand(long sleepTime) {
		time = sleepTime;
	}
	
	public void run() {
		ThreadSupport.sleep(time);
	}

}