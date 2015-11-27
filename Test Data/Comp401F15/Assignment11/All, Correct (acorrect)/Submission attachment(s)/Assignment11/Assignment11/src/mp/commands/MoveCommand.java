package mp.commands;


import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import util.annotations.Tags;
@Tags("MoveCommand")
public class MoveCommand implements Runnable{
	AvatarInterface theAvatar;
	int theChangeInX, theChangeInY;
	public MoveCommand(AvatarInterface anAvatar, int aChangeInX, int aChangeInY){
		theAvatar = anAvatar;
		theChangeInX = aChangeInX;
		theChangeInY = aChangeInY;
	}
	
	public void run() {
		theAvatar.move(theChangeInX, theChangeInY);
	}
}
