package mp.bean.animation;

import mp.ui.Avatar;

public interface AvatarAnimator {
	
	public void animateAvatar(BroadcastingClearanceManager clearanceManager, Avatar avatar, int step, int clapCount);

}
