package mp.bean.commands;

import mp.bean.animation.AvatarAnimator;
import mp.bean.animation.BroadcastingClearanceManager;
import mp.ui.Avatar;
import util.annotations.Tags;

@Tags({"CoordinatedAnimationCommand"})
public class CoordinatedAnimationCommand implements Runnable {

	AvatarAnimator animator;
	Avatar avatar;
	int step, stepCount;
	BroadcastingClearanceManager cManager;

	public CoordinatedAnimationCommand(BroadcastingClearanceManager clearanceManager, AvatarAnimator anAnimator, Avatar anAvatar, int animStep, int animStepCount) {
		animator = anAnimator;
		avatar = anAvatar;
		step = animStep;
		stepCount = animStepCount;	
		cManager = clearanceManager;
	}
	
	public void run() {
		animator.animateAvatar(cManager, avatar, step, stepCount);
	}

}
