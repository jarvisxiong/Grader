package grail.animation;

import grail.avatar.Avatar;
import grail.helpers.ClearanceManager;
import util.misc.ThreadSupport;

public class BasicLockstepAnimator implements LockstepAnimator {
	private ClearanceManager manager;
		
	public BasicLockstepAnimator(ClearanceManager manager) {
		this.manager = manager;
	}

	
	public synchronized void clapAvatar(Avatar toAnimate, int timesToClap) {
		final int armRotation = 7;
		final int sleepTime = 1000;
		int index = 0;
		
		while (index < timesToClap) {
			toAnimate.getArms().getLeftLine().rotate(armRotation);
			toAnimate.getArms().getRightLine().rotate(-armRotation);
			ThreadSupport.sleep(sleepTime);
			this.manager.proceedAll();
			toAnimate.getArms().getLeftLine().rotate(-armRotation);
			toAnimate.getArms().getRightLine().rotate(armRotation);
			ThreadSupport.sleep(sleepTime);
			this.manager.proceedAll();
			index++;
		}
	}

	public void marchAvatar(Avatar toAnimate, int distanceToMarch) {
		final int moveIncrementX = 10;
		final int moveIncrementY = 0;
		final int rotationAmount = 8;
		int distanceMarched = 0;
		int position = 0;
		while (distanceMarched < distanceToMarch) {
			if (position == 0) {
				this.manager.waitForProceed();
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(-rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				position = 1;
			} else {
				this.manager.waitForProceed();
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				position = 0;
			}
		}
		
	}

}
