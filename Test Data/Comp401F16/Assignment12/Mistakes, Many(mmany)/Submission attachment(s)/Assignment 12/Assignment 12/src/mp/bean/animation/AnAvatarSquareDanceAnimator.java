package mp.bean.animation;

import mp.ui.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"Animator"})

public class AnAvatarSquareDanceAnimator implements AvatarAnimator {
	
	private static final int MAX = 20;
	
	@Tags({"animateAvatar"})
	public synchronized void animateAvatar(BroadcastingClearanceManager clearanceManager, Avatar avatar, int step, int pauseTime) {
		for (int i = 0; i<= MAX; i++) {
			avatar.move(step,0);
			ThreadSupport.sleep(pauseTime);			
		}
		for (int i = 0; i<= MAX; i++) {
			avatar.move(0, step);
			ThreadSupport.sleep(pauseTime);			
		}
		for (int i = 0; i<= MAX; i++) {
			avatar.move(-step,0);
			ThreadSupport.sleep(pauseTime);			
		}
		for (int i = 0; i<= MAX; i++) {
			avatar.move(0, -step);
			ThreadSupport.sleep(pauseTime);			
		}
	}

}
