package grail.animation;

import grail.avatar.Avatar;

public class LockstepCommandObject implements Runnable {
	private LockstepAnimator animator;
	private String command;
	private int distanceToWalkorClap;
	private Avatar avatar;
	
	public LockstepCommandObject(LockstepAnimator animator,String command, Avatar toAnimate, int distanceToWalkOrClap ) {
		this.animator = animator;
		this.command = command;
		this.distanceToWalkorClap = distanceToWalkOrClap;
		this.avatar = toAnimate;
	}

	public void run() {
		if ("march".equals(this.command)) {
			this.animator.marchAvatar(this.avatar, this.distanceToWalkorClap);
		} else if ("clap".equals(this.command)) {
			this.animator.clapAvatar(this.avatar, this.distanceToWalkorClap);
		}
		
		
	}

}
