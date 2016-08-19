package mp.bean.commands;

import mp.bean.Table;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"CallCommand"})

public class CallCommand implements Runnable {
	
	String name;
	Runnable command;
	Table<String, Runnable> environment;

	public CallCommand(String aName, Table<String, Runnable> anEnvironment) {
		name = aName;
		environment = anEnvironment;
	}
	
	public void run() {
		command = environment.get(name);
		command.run();
	}

}