package grail.interfaces;

public interface UndoableRunnable extends Runnable {
	public void undo();
	public void redo();
}
