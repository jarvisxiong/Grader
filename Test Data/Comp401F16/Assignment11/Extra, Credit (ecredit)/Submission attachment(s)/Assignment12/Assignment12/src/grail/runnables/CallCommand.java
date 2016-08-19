package grail.runnables;

import grail.helpers.Table;
import util.annotations.Tags;

@Tags({"CallCommand"})
public class CallCommand implements Runnable{
	private String command;
	private Table<String,Runnable> commandList;
	
	public CallCommand(String commandName, Table<String,Runnable> list) {
		this.command = commandName;
		this.commandList = list;
	}

	public void run() {
		if (this.commandList.get(this.command) != null) {
			((Runnable) this.commandList.get(this.command)).run();
		} else {
			//do nothing
		}
	}

}
