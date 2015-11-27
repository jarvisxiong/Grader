package mp.animator;

import grail.shape.Avatar;
import grail.shape.AvatarInterface;

public interface DemoAnimator
{
	public void animateAvatar(AvatarInterface anAvatar, int x, int y, int animationStep, int animationPauseTime);
}
