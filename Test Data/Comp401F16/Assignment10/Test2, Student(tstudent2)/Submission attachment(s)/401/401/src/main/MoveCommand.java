package main;

import mp.Avatar;
import util.misc.ThreadSupport;

public class MoveCommand implements Runnable {
	
	Avatar avatar;
	int moveX, moveY;
	
	public MoveCommand(Avatar anAvatar, int newX, int newY){
		avatar = anAvatar;
		moveX = newX;
		moveY = newY;
	}
	
	public void run() {
		avatar.animatedMove(moveX, moveY);
		ThreadSupport.sleep(1000);
	}

}
