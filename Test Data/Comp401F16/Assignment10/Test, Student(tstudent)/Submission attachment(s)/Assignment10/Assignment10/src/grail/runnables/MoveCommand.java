package grail.runnables;

import grail.avatar.Avatar;

public class MoveCommand implements Runnable{
	private Avatar avatar;
	private int x,y;
	
	public MoveCommand(Avatar avatar, int moveX, int moveY) {
		this.avatar = avatar;
		this.x = moveX;
		this.y = moveY;
	}

	public void run() {
		this.avatar.move(this.x, this.y);
		
	}

}
