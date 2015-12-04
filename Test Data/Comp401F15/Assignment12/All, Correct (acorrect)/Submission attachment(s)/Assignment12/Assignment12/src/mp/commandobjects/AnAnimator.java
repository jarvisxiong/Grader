package mp.commandobjects;

import java.util.Random;

import mp.shapes.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"Animator"})
public class AnAnimator implements Animator{

	@Tags({"animateAvatar"})
	@Override
	public synchronized void animateAvatar(Avatar avatar) {
		final int scannerStringRefreshWait = 20;
		final double rotateAmount = Math.PI/scannerStringRefreshWait;
		int i = 0;
		while(i < 50){
			i++;
			avatar.getText().setText(avatar.getText().getText()+"L");
			avatar.getArms().getRightLine().rotate(rotateAmount);
			avatar.getArms().getLeftLine().rotate(-rotateAmount);
			avatar.getLegs().getRightLine().rotate(rotateAmount);
			avatar.getLegs().getLeftLine().rotate(-rotateAmount);
			avatar.move(randInt(-1, 1), randInt(-1, 1));
			ThreadSupport.sleep(scannerStringRefreshWait);
		}
	}
	
	private int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
