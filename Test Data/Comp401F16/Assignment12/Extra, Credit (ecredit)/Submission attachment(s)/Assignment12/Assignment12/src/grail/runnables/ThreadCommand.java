package grail.runnables;

import grail.helpers.Table;
import util.annotations.Tags;

@Tags({"ThreadCommand"})
public class ThreadCommand implements Runnable{
	private String commandToExecute;
	private Table<String,Runnable> toList;
	
	public ThreadCommand(String command, Table<String,Runnable> list) {
		this.commandToExecute = command;
		this.toList = list;
	}

	public void run() {
		try {	
			Thread toRun = new Thread(((Runnable) this.toList.get(this.commandToExecute)));
			toRun.start();
		} catch (ClassCastException e) {
			//do nothing
		} catch (Exception e) {
			//do nothing
		}
	}

}
