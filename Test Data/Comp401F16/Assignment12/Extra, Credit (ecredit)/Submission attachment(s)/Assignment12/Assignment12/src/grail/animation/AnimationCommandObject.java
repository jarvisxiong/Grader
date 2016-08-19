package grail.animation;

import grail.avatar.Avatar;
import grail.helpers.ClearanceManager;
import util.annotations.Tags;

@Tags({"AnimatingCommand"})
public class AnimationCommandObject implements Runnable {
	private Avatar currentAvatar;
	private Animator animation;
	private String command;
	private String walkCommand = "walk";
	private String clapCommand = "clap";
	private int distanceToWalkorTimeToClap, sleepTime;
	private ClearanceManager manage;
	
	public AnimationCommandObject(Animator toAnimate, String command, Avatar cAvatar) {
		this.currentAvatar = cAvatar;
		this.animation = toAnimate;
		this.command = command;
	}
	
	public AnimationCommandObject(Animator toAnimate, String command, Avatar cAvatar, int distanceToWalkorTimeToClap) {
		this.currentAvatar = cAvatar;
		this.animation = toAnimate;
		this.command = command;
		this.distanceToWalkorTimeToClap = distanceToWalkorTimeToClap;
	}
	
	public AnimationCommandObject(Animator toAnimate, String command, Avatar cAvatar, int distanceToWalk, int sleepTime) {
		this.currentAvatar = cAvatar;
		this.animation = toAnimate;
		this.command = command;
		this.distanceToWalkorTimeToClap = distanceToWalk;
		this.sleepTime = sleepTime;
	}
	
	public AnimationCommandObject(Animator toAnimate, ClearanceManager manage, String command, Avatar cAvatar) {
		this.currentAvatar = cAvatar;
		this.animation = toAnimate;
		this.command = command;
		this.manage = manage;
	}
	
	public AnimationCommandObject(Animator toAnimate, ClearanceManager manage,  String command, Avatar cAvatar, int distanceToWalkorTimeToClap) {
		this.currentAvatar = cAvatar;
		this.animation = toAnimate;
		this.command = command;
		this.distanceToWalkorTimeToClap = distanceToWalkorTimeToClap;
		this.manage = manage;
	}
	
	public AnimationCommandObject(Animator toAnimate, ClearanceManager manage, String command, Avatar cAvatar, int distanceToWalk, int sleepTime) {
		this.currentAvatar = cAvatar;
		this.animation = toAnimate;
		this.command = command;
		this.distanceToWalkorTimeToClap = distanceToWalk;
		this.sleepTime = sleepTime;
		this.manage = manage;
	}
		
	public void run() {
		if (this.manage == null) {
			runWithoutClearance();
		} else {
			this.manage.waitForProceed();
			runWithoutClearance();
		}
	}
	
	private void runWithoutClearance() {
		if (walkCommand.equals(this.command) && this.distanceToWalkorTimeToClap == 0 && this.sleepTime == 0) {
			this.animation.walkAvatar(this.currentAvatar); /*- missingMethodCall: (AnimationCommandObject.java:1) has not made expected call @Animator!@AnimateAvatar:@Avatar-
			 >void    This is that call.*/
		} else if (walkCommand.equals(this.command) && this.sleepTime == 0) {
			this.animation.walkAvatar(this.currentAvatar, this.distanceToWalkorTimeToClap);
		} else if (walkCommand.equals(this.command)) {
			this.animation.walkAvatar(this.currentAvatar, this.distanceToWalkorTimeToClap, this.sleepTime);
		} else if (clapCommand.equals(this.command)) {
			this.animation.clapAvatar(this.currentAvatar, this.distanceToWalkorTimeToClap);
		}
	}
	

}
