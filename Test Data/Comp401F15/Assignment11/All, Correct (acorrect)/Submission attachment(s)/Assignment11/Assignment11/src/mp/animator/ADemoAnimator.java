package mp.animator;

import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags("Animator")
public class ADemoAnimator implements DemoAnimator
{
	@Tags("animateAvatar")
	public synchronized void animateAvatar(AvatarInterface anAvatar, int dx,
			int dy, int animationStep, int animationPauseTime)
	{

		//animate move avatar
			anAvatar.move(dx, dy);

	}

//	@Override
//	public void animateAvatar(AvatarInterface anAvatar, int x, int y,
//			int animationStep, int animationPauseTime) {
//		// TODO Auto-generated method stub
//		
//	}
}
