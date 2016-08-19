package mp.bean.commands;

import mp.ui.Avatar;
import util.annotations.Tags;

@Tags({"MoveCommand"})

public class MoveCommand implements Runnable {
	
	Avatar avatar;
	int moveX, moveY;

	public MoveCommand(Avatar anAvatar, int anX, int aY) {
		avatar = anAvatar;
		moveX = anX;
		moveY = aY;
	}
	public void run() {
		avatar.move(moveX, moveY);
	}

}
