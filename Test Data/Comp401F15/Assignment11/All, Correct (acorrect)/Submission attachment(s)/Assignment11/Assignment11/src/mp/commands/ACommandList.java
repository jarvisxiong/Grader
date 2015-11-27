package mp.commands;

import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"CommandList"})
public class ACommandList implements CommandList{
	ArrayList<Runnable> commandList = new ArrayList<Runnable>();
	
	@Tags("Add")
	public void add(Runnable aRunnable){
		commandList.add(aRunnable);
	}
	
	public void run() 
	{
		//Runs each command in order
		for (int index = 0; index < commandList.size(); index++)
		{
			commandList.get(index).run();
		}
	}

}
