package mp.command;

import mp.Scene.Avatar;

public class TwoAnimatingCommand implements AnimatingCommand{
	Animator aA;
	Avatar xX;
	public TwoAnimatingCommand(Animator aa, Avatar xx){
		aA=aa;
		xX=xx;
	}
	@Override
	public void run() {
		aA.clapRightHand(xX);
	}

}
