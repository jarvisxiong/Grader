package mp.bean.commands;

import util.annotations.Tags;

@Tags({"Repeat"})

public class RepeatCommand implements Runnable {

	Runnable command;
	int count;
	
	public RepeatCommand(int aCount, Runnable aCommand){
		count = aCount;
		command = aCommand;		
	}
	
	public void run() {
		for (int i = 0; i<count; i++){
		command.run();
		}
	}

}
