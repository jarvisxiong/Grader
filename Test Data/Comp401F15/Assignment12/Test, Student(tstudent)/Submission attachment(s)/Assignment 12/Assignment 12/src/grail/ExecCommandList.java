package grail;

import java.util.List;
import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"CommandList"})
public class ExecCommandList implements ExecutableCommandList{
	List<Runnable> commands = new ArrayList<Runnable>();
	
	public ExecCommandList() {}
	
	public void add(Runnable token) {
		commands.add(token);
	}
	
	public void run() {
		for (int x = 0; x < commands.size(); x++) {
			Runnable temp = commands.get(x);
			temp.run();
		}
	}
	
	
}
