package mp.command;

import mp.Scene.Avatar;
import util.annotations.Tags;

@Tags({"AnimatingCommand"})
public class AnAnimatingCommand implements AnimatingCommand{
	Animator theAnimator;
	Avatar theMan;
	int x;
	int y;
	public AnAnimatingCommand(Animator newAnimator, Avatar aMan, int deltaX, int deltaY){
		theAnimator=newAnimator;
		theMan=aMan;
		x=deltaX;
		y=deltaY;
	}
	
	public void run(){
		theAnimator.animateXY(theMan, x, y);
	}
}
