package mp.commandobjects;

import mp.shapes.Avatar;
import util.misc.ThreadSupport;

public class AGuardAnimator extends AnAnimator implements Animator{

	@Override
	public synchronized void animateAvatar(Avatar avatar) {
		final int wait = 200;
		final double four = 4.0;
		final double angle1 = Math.PI/four;
		final double angle2 = 7*angle1;
		
		while(true){
			ThreadSupport.sleep(wait);
			avatar.getArms().getLeftLine().setAngle(angle1);
			avatar.getArms().getLeftLine().rotate(0);
			avatar.getArms().getRightLine().setAngle(angle2);
			avatar.getArms().getRightLine().rotate(0);
			ThreadSupport.sleep(wait);
			avatar.getArms().getLeftLine().setAngle(0);
			avatar.getArms().getLeftLine().rotate(0);
			avatar.getArms().getRightLine().setAngle(0);
			avatar.getArms().getRightLine().rotate(0);
		}
	}
	
}
