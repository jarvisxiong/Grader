package mp.commandobjects;

public interface CommandList extends Runnable{
	public void add(Runnable command);
}
