package grail.runnables;

import java.util.ArrayList;
import java.util.List;

import grail.interfaces.UndoableRunnable;
import util.annotations.Tags;

@Tags({"CommandList","Undoable"})
public class CommandList implements RunnableList {
	private List<Runnable> commandList;
	private boolean beenUndone;
	
	public CommandList() {
		commandList = new ArrayList<Runnable>();
		beenUndone = false;
	}

	public void run() {
		for(int i = 0; i < commandList.size(); i++) {
			commandList.get(i).run();
		}
		beenUndone = false;
	}
	
	@Tags({"add"})
	public void add(Runnable toAdd) {
		this.commandList.add(toAdd);
		
	}

	public void clear() {
		this.commandList.clear();
	}

	public void undo() {
		if (!beenUndone) {
			for (int i = 0; i < this.commandList.size(); i ++) {
				try {
					((UndoableRunnable) this.commandList.get(i)).undo();
				} catch (ClassCastException ce) {
					//do nothing
				}
			}	
		}
		this.beenUndone = true;
		
	}

	public void redo() {
		if (beenUndone) {
			for (int i = 0; i < this.commandList.size(); i ++) {
				try {
					((UndoableRunnable) this.commandList.get(i)).redo();
				} catch (ClassCastException ce) {
					//do nothing
				}
			}	
		}
		this.beenUndone = false;
	}

}
