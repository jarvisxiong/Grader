package mp.bean.animation;

import mp.ui.AdvLocatable;
import mp.ui.Avatar;
import mp.ui.RotatingLine;
import util.misc.ThreadSupport;

public class AnAvatarClapAnimator implements AvatarAnimator {
	
	private static final int MAX = 5, ROTATE_TO_LEFT_ARM = 100;

	public void animateAvatar(BroadcastingClearanceManager clearanceManager, Avatar avatar, int step, int pauseTime) {
//		AdvLocatable leftHandLoc = avatar.getArms().getLeftLine().getAdjLocation();
//		RotatingLine rightArm = avatar.getArms().getRightLine();
//		rightArm.setAdjLocation(leftHandLoc);
		RotatingLine rightArm = avatar.getArms().getRightLine();
		rightArm.rotate(ROTATE_TO_LEFT_ARM);
		for (int i = 0; i<= MAX; i++) {
			rightArm.rotate(step);
			ThreadSupport.sleep(pauseTime);
			rightArm.rotate(-step);
			ThreadSupport.sleep(pauseTime);
			// where proceed all goes
		}
	}
}
