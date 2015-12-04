package grail;

import main.BroadcastingClearanceManager;
import mp.Avatar;
import util.annotations.Tags;
@Tags({"AnimatingCommand"})

public class AnAnimatingCommand implements AnimatingCommand{
	AvatarAnimator animator = new AnAvatarAnimator();
	Avatar avatar;
	int x,y;
	BroadcastingClearanceManager manager;
	
	public AnAnimatingCommand (Avatar anAvatar) {avatar = anAvatar;}
	public AnAnimatingCommand (Avatar anAvatar, BroadcastingClearanceManager manager1) {//maybe
		avatar = anAvatar;
		manager = manager1;
	}
		
	
	public AnAnimatingCommand (Avatar anAvatar, int X, int Y) {
		avatar = anAvatar;
		x = X;
		y = Y;
	}

	public void run() {
		manager.waitForProceed();//bug
		animator.walk(avatar);		
	}
}
