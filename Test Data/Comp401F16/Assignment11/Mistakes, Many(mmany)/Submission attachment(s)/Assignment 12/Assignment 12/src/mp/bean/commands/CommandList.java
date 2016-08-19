package mp.bean.commands;

public interface CommandList extends Runnable {

	public void add(Runnable command);
	
	public int size();
	
}
