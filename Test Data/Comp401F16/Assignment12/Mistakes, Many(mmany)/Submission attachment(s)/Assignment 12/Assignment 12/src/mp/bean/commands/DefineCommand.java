package mp.bean.commands;

import mp.bean.Table;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"DefineCommand"})

public class DefineCommand implements Runnable {
	
	String name;
	Runnable command;
	Table<String, Runnable> environment;

	public DefineCommand(String aName, Runnable aCommand, Table<String, Runnable> anEnvironment) {
		name = aName;
		command = aCommand;
		environment = anEnvironment;
	}
	
	public void run() {
		environment.put(name, command);
	}

}