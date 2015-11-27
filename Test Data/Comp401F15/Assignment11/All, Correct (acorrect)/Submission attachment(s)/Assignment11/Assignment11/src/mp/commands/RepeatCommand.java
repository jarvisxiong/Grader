package mp.commands;

import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags("Repeat")
//@Tags("RepeatCommand")
public class RepeatCommand implements Runnable {
	int repetitions;
	Runnable theRunnable;
	public RepeatCommand(int repetitionCount, Runnable aRunnable){
		repetitions = repetitionCount;
		theRunnable = aRunnable;
	}
	
	@Override
	public void run() {
		//Repeats the command for number of repetitions
		for(int index = 0; index < repetitions; index++){
			theRunnable.run();
			final int sleepTime=1000;
			ThreadSupport.sleep(sleepTime);
		}
	}

}
