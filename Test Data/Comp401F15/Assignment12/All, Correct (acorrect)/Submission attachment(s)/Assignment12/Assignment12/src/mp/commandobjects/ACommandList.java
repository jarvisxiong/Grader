package mp.commandobjects;

import java.util.ArrayList;
import java.util.List;

import util.annotations.Tags;

@Tags({"CommandList"})
public class ACommandList implements CommandList{
	private List<Runnable> commands;
	
	public ACommandList(){
		commands = new ArrayList<Runnable>();
	}
	
	@Override
	public void run() {
		for(Runnable command: commands){
			if(command != null){
				command.run();
			}
		}
	}

	@Tags({"add"})
	@Override
	public void add(Runnable command) {
		commands.add(command);
	}

}
