package mp.bean.animation;

import mp.ui.Avatar;
import util.annotations.Tags;

@Tags({"CoordinatedAnimator"})

public class CoordinatedAnimator implements AvatarAnimator {

	@Tags({"animateAvatar"})
	public void animateAvatar(BroadcastingClearanceManager clearanceManager, Avatar avatar, int step, int clapCount) {
		
		for (int i = 0; i<= clapCount; i++) {
			avatar.move(step,0);
			clearanceManager.waitForProceed();		
		}
		
	}
}
