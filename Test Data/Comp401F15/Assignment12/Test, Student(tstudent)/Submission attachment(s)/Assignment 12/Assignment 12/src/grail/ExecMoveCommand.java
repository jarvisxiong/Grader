package grail;

import mp.Avatar;
import util.annotations.Tags;

@Tags({"MoveCommand"})
public class ExecMoveCommand implements ExecutableMove {
	Avatar subject;
	int moveX, moveY;
	public ExecMoveCommand (Avatar anAvatar, int X, int Y) {
		subject = anAvatar;
		moveX = X;
		moveY = Y;
	}
	public void run() {
		subject.move(moveX, moveY);
	}

}
