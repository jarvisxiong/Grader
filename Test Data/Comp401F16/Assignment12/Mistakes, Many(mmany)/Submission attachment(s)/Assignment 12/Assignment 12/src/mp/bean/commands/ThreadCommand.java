package mp.bean.commands;

import mp.bean.Table;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"ThreadCommand"})

public class ThreadCommand implements Runnable {
	
	String name;
	Runnable command;
	Table<String, Runnable> environment;

	public ThreadCommand(String aName, Table<String, Runnable> anEnvironment) {
		name = aName;
		environment = anEnvironment;
	}
	
	public void run() {
		command = environment.get(name);
		Thread thread = new Thread(command);
		thread.start();
	}

}