package grail.runnables;

import grail.interfaces.UndoableRunnable;

public interface RunnableList extends UndoableRunnable {
	public void add(Runnable toAdd);
	public void clear();
}
