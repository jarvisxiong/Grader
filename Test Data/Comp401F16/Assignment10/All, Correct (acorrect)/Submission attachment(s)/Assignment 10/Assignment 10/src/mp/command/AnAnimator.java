package mp.command;

import mp.Scene.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"Animator"})
public class AnAnimator implements Animator{
	final int shi=10;
	final int yibai=100;
	final int sishiwu=45;
@Tags({"animateAvatar"})
	public void animateXY(Avatar aMan, int deltaX, int deltaY){
	
//		int curX=aMan.getHead().getX();
//		int curY=aMan.getHead().getY();
		int i=deltaX/shi;
		int j=deltaY/shi;
		for(int m=0;m<shi;m++){
			aMan.moveThis(i, j);
			ThreadSupport.sleep(yibai);
		}
	}
	public void clapRightHand(Avatar aMan){
		aMan.getArms().getRightLine().rotate(-sishiwu);
		aMan.getArms().getLeftLine().rotate(sishiwu);
		aMan.getLegs().getLeftLine().rotate(sishiwu);
		aMan.getLegs().getRightLine().rotate(-sishiwu);
		ThreadSupport.sleep(yibai);
		aMan.getArms().getRightLine().rotate(sishiwu);
		aMan.getArms().getLeftLine().rotate(-sishiwu);
		aMan.getLegs().getLeftLine().rotate(-sishiwu);
		aMan.getLegs().getRightLine().rotate(sishiwu);

	}
}
