package grail.runnables;

import grail.avatar.Avatar;
import grail.interfaces.UndoableRunnable;
import util.annotations.Tags;

@Tags({"RotateLeftArmCommand","Undoable"})
public class RotateLeftArmCommand implements UndoableRunnable {
	private Avatar cAvatar;
	private int toRotate;
	private boolean beenUndone;
	
	public RotateLeftArmCommand(Avatar avatar, int rotate) {
		this.cAvatar = avatar;
		this.toRotate = rotate;
		beenUndone = false;
	}

	public void run() {
		this.cAvatar.getArms().getLeftLine().rotate(this.toRotate);
		beenUndone = false;
	}

	public void undo() {
		if (!beenUndone) {
			this.cAvatar.getArms().getLeftLine().rotate(-this.toRotate);
			beenUndone = true;
		}
		
	}

	public void redo() {
		if (beenUndone) {
			this.cAvatar.getArms().getLeftLine().rotate(this.toRotate);
			beenUndone = false;
		}		
	}

}
