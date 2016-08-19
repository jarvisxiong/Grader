package mp.command;

import mp.Scene.Avatar;

public interface Animator {
	public void animateXY(Avatar aMan, int deltaX, int deltaY);
	public void clapRightHand(Avatar aMan);
}
