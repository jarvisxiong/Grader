package mp.animator;

import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import util.annotations.Tags;
import util.misc.ThreadSupport;
@Tags("GuardAnimator")
//for extra credit
public class AGuardDemoAnimator implements GuardDemoAnimator {
	public synchronized void clap(AvatarInterface theGuard, int totalNumberOfClaps,
			int animationPauseTime) {
		int currentNumberOfClaps = 0;
		totalNumberOfClaps = 2*totalNumberOfClaps; //Animation claps once for every two "claps"
		while (currentNumberOfClaps < totalNumberOfClaps) {
			//Bring arms together on even number
			if (currentNumberOfClaps % 2 == 0) {
				theGuard.getArms().getLeftLine().rotate(27);
				theGuard.getArms().getRightLine().rotate(27);
				ThreadSupport.sleep(animationPauseTime);
			}
			//Spreads arms apart on odd number
			else {
				theGuard.getArms().getLeftLine().rotate(27 + CLAP_ANGLE);
				theGuard.getArms().getRightLine().rotate(27 - CLAP_ANGLE);
				ThreadSupport.sleep(animationPauseTime);
			}
			currentNumberOfClaps++;
		}
	}
}
