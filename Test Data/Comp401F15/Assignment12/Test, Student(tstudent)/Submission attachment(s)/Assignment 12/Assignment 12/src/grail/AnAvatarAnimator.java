package grail;

import mp.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;
@Tags({"Animator"})

public class AnAvatarAnimator implements AvatarAnimator{
	int sleepDelay = 10;
	final int LINE_INCREMENTS = 50;
	final int MAX_X = 150;
	final int STEP = 3;
	
@Tags({"AnimateAvatar"})
	public synchronized void walk(Avatar anAvatar) {
		int initialX = 0;
		
		while (initialX < MAX_X) {
			anAvatar.move(STEP, 0);
			initialX += STEP;
			ThreadSupport.sleep(sleepDelay);
		}
		
	}

@Tags({"AnimateAvatar"})
	public void walk(Avatar anAvatar, int finalX, int finalY) {
		int initialX = 0;
		int initialY = 0;

		while (initialX < finalX && initialY < finalY) {
			int stepX = finalX / LINE_INCREMENTS;
			int stepY = finalY / LINE_INCREMENTS;
			anAvatar.move(stepX, stepY);
			initialX += stepX;
			initialY += stepY;
			ThreadSupport.sleep(sleepDelay);
		}
	}
}
