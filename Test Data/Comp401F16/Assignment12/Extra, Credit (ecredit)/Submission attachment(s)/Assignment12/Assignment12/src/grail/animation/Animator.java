package grail.animation;

import grail.avatar.Avatar;
import util.annotations.Tags;

@Tags({"Animator"})
public interface Animator {
	@Tags({"animateAvatar"})
	public void walkAvatar(Avatar toAnimate);	
	public void walkAvatar(Avatar toAnimate, int distanceToWalk);
	public void walkAvatar(Avatar toAnimate, int distanceToWalk, int sleepTime);
	public void clapAvatar(Avatar toAnimate, int timeToClap);

}
