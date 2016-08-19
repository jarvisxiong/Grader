package mp.commandobjects;

import util.annotations.Tags;

@Tags({"Repeat"})
public class RepeatCommandObject implements Runnable{
	private Runnable command;
	private int count;
	
	public RepeatCommandObject(Runnable command, int count){
		this.command = command;
		this.count = count;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < count; i++){
			command.run();
		}
	}
	
}
