package mp.commands;

import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import util.annotations.Tags;
import mp.animator.DemoAnimator;

@Tags("AnimatingCommand")
public class DemoCommand implements Runnable {
	DemoAnimator theDemoAnimator;
	AvatarInterface theAvatar;
	int theChangeInX, theChangeInY;
	
	
	public DemoCommand(AvatarInterface anAvatar, int aChangeInX, int aChangeInY, DemoAnimator aDemoAnimator) {
		theAvatar = anAvatar;
		theChangeInX = aChangeInX;
		theChangeInY = aChangeInY;
		theDemoAnimator = aDemoAnimator;
	}

	public void run() {
		theDemoAnimator.animateAvatar(theAvatar, theChangeInX, theChangeInY, 5, 250);
	}
}
