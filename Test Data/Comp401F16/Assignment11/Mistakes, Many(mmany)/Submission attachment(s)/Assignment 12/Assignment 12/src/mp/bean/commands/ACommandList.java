package mp.bean.commands;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import util.annotations.Tags;

@Tags({"CommandList"})

public class ACommandList implements CommandList {
	
	ArrayList<Runnable> commands;
	
	public ACommandList() {
		commands = new ArrayList<Runnable>();
	}
	
	@Tags("add")
	public void add(Runnable command){
		commands.add(command);
	}

	public void run() {
		for(int i = 0; i<commands.size(); i++) {
			commands.get(i).run();
		}
	}
	
	public int size() {
		return commands.size();
	}

}
