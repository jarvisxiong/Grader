package mp.animator;

import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import util.annotations.Tags;
@Tags("Guard Animator")
public interface GuardDemoAnimator {
	final int CLAP_ANGLE = 30;
	public void clap(AvatarInterface theGuard, int totalNumberOfClaps,
			int animationPauseTime);
}
