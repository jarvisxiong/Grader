package mp.commandobjects;

import mp.shapes.Avatar;
import util.annotations.Tags;

@Tags({"MoveCommand"})
public class MoveCommandObject implements Runnable{
	private Avatar avatar;
	private int moveX;
	private int moveY;
	
	public MoveCommandObject(Avatar avatar, int moveX, int moveY){
		this.avatar = avatar;
		this.moveX = moveX;
		this.moveY = moveY;
	}
	
	@Override
	public void run() {
		avatar.move(moveX, moveY);
	}

}
