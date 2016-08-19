package grail.runnables;

import grail.avatar.Avatar;
import grail.interfaces.UndoableRunnable;
import util.annotations.Tags;

@Tags({"MoveCommand", "Undoable"})
public class MoveCommand implements UndoableRunnable{
	private Avatar avatar;
	private int x,y;
	private boolean beenUndone;
	
	public MoveCommand(Avatar avatar, int moveX, int moveY) {
		this.avatar = avatar;
		this.x = moveX;
		this.y = moveY;
		beenUndone = false;
	}

	public void run() {
		this.avatar.move(this.x, this.y);	
	}

	public void undo() {
		if (!this.beenUndone) {
			this.avatar.move(-this.x, -this.y);
			beenUndone = true;
		}
	}

	public void redo() {
		if (this.beenUndone) {
			this.avatar.move(this.x, this.y);
			beenUndone = false;
		}	
	}

}
