package grail.scene;

import grail.BodyInterface;
import util.annotations.Tags;


@Tags({"MoveCommand"})
public class MoveCommand implements Runnable {
	
	BodyInterface avatar;
	int x,y;

	public MoveCommand(BodyInterface anAvatar, int anX, int aY){
		avatar = anAvatar;
		x = anX;
		y = aY;
	}
	
	public void run() {
		avatar.moveBody(x, y);
		
	}

}
