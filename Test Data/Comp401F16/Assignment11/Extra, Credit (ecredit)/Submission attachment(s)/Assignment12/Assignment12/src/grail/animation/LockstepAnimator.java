package grail.animation;

import grail.avatar.Avatar;

public interface LockstepAnimator {
	public void marchAvatar(Avatar toAnimate, int timeToClap);
	public void clapAvatar(Avatar toAnimate, int timeToClap);
}
