package mp.bean.animation;

import mp.ui.AdvLocatable;
import mp.ui.Avatar;
import mp.ui.RotatingLine;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"CoordinatedAnimator"})
public class AnAvatarProceedClapAnimator implements AvatarAnimator {
	
	private static final int CLAP_PAUSE_TIME = 500, ROTATE_TO_LEFT_ARM = 100;
	int clap;

	@Tags({"animateAvatar"})
	public void animateAvatar(BroadcastingClearanceManager clearanceManager, Avatar avatar, int clapSize, int clapCount) {

		RotatingLine rightArm = avatar.getArms().getRightLine();
		rightArm.rotate(ROTATE_TO_LEFT_ARM);
		clap = clapSize;
		for (int i = 0; i<= (clapCount); i++) {
			clap = -clap;
			rightArm.rotate(clap);
			ThreadSupport.sleep(CLAP_PAUSE_TIME);
			if (clap<0){
			clearanceManager.proceedAll();
			}
		}
	}
}
