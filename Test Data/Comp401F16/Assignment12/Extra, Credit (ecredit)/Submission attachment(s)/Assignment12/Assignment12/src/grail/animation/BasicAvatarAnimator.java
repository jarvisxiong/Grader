package grail.animation;

import grail.avatar.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"Animator"})
public class BasicAvatarAnimator implements Animator {

	
	@Tags({"animateAvatar"})
	public synchronized void walkAvatar(Avatar toAnimate) {
		final int distanceToMarch = 100;
		final int moveIncrementX = 10;
		final int moveIncrementY = 0;
		final int rotationAmount = 8;
		final int sleepBetweenTimeMS = 200;
		int distanceMarched = 0;
		int position = 0;
		while (distanceMarched < distanceToMarch) {
			if (position == 0) {
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(-rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 1;
			} else {
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 0;
			}
		}
		
	}
	
	public synchronized void walkAvatar(Avatar toAnimate, int distanceToWalk) {
		int distanceToMarch = distanceToWalk;
		final int moveIncrementX = 10;
		final int moveIncrementY = 0;
		final int rotationAmount = 8;
		final int sleepBetweenTimeMS = 200;
		int distanceMarched = 0;
		int position = 0;
		while (distanceMarched < distanceToMarch) {
			if (position == 0) {
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(-rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 1;
			} else {
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 0;
			}
		}
	}
	
	public synchronized void walkAvatar(Avatar toAnimate, int distanceToWalk, int sleepTime) {
		int distanceToMarch = distanceToWalk;
		final int moveIncrementX = 10;
		final int moveIncrementY = 0;
		final int rotationAmount = 8;
		int sleepBetweenTimeMS = sleepTime;
		int distanceMarched = 0;
		int position = 0;
		while (distanceMarched < distanceToMarch) {
			if (position == 0) {
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(-rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 1;
			} else {
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 0;
			}
		}
		
	}

	public synchronized void clapAvatar(Avatar toAnimate, int secondsToClap) {
		final int clapMS = 200;
		final int fullClapMS = 400;
		final int msInASecond = 1000;
		final int clapTimeInMS = secondsToClap * msInASecond;
		final int clapRotate = 4;
		final double originalArmLeft = toAnimate.getArms().getLeftLine().getAngle();
		final double originalArmRight = toAnimate.getArms().getRightLine().getAngle();
		toAnimate.getArms().getLeftLine().setAngle(Math.PI);
		toAnimate.getArms().getRightLine().setAngle(Math.PI);
		
		int msClapped = 0;
		while (msClapped < clapTimeInMS) {
			toAnimate.getArms().getRightLine().rotate(clapRotate);
			ThreadSupport.sleep(clapMS);
			toAnimate.getArms().getRightLine().rotate(-clapRotate);
			ThreadSupport.sleep(clapMS);
			msClapped = msClapped + fullClapMS;
		}
		
		toAnimate.getArms().getRightLine().setAngle(originalArmRight);
		toAnimate.getArms().getLeftLine().setAngle(originalArmLeft);
	}


}
