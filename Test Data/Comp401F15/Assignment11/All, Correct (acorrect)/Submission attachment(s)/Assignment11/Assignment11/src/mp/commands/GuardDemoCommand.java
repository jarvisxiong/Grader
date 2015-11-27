package mp.commands;

import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import util.annotations.Tags;
import mp.animator.GuardDemoAnimator;
@Tags("guard animating command")
public class GuardDemoCommand implements Runnable{
	GuardDemoAnimator theGuardDemoAnimator;
	AvatarInterface theGuard;
	int totalNumberOfClaps;
	
	public GuardDemoCommand(AvatarInterface guard, int numberOfClaps, GuardDemoAnimator aGuardDemoAnimator){
		theGuard = guard;
		theGuardDemoAnimator = aGuardDemoAnimator;
		totalNumberOfClaps = numberOfClaps;
	}
	
	public void run() {
		theGuardDemoAnimator.clap(theGuard, totalNumberOfClaps, 125);
	}

}
