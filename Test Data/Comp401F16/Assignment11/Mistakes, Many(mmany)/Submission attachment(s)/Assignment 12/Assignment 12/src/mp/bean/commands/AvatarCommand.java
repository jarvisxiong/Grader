package mp.bean.commands;

import mp.bean.animation.AvatarAnimator;
import mp.bean.animation.BroadcastingClearanceManager;
import mp.ui.Avatar;
import util.annotations.Tags;

@Tags({"AnimatingCommand"})

public class AvatarCommand implements Runnable {
	
	AvatarAnimator animator;
	Avatar avatar;
	int step, pauseTime;
	BroadcastingClearanceManager cManager;

	public AvatarCommand(BroadcastingClearanceManager clearanceManager, AvatarAnimator anAnimator, Avatar anAvatar, int animStep, int animPauseTime) {
		animator = anAnimator;
		avatar = anAvatar;
		step = animStep;
		pauseTime = animPauseTime;	
		cManager = clearanceManager;
	}
	public void run() {
		if (cManager != null) {
		cManager.waitForProceed();
		}
		animator.animateAvatar(cManager, avatar, step, pauseTime);
	}
	
}
