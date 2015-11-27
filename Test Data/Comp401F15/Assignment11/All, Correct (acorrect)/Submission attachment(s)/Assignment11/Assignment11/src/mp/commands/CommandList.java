package mp.commands;

public interface CommandList extends Runnable {
	public void add(Runnable aRunnable);
}
