package main;

import mp.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"AnimatingCommand"})
public class AnimatingCommands implements Runnable{
	
	Animator animation;
	
	public AnimatingCommands(Avatar newAvatar){
		
		Animator animate = new AnimatingAvatars(newAvatar);
		animation = animate;
	}
	
	public void run() {
		animation.animateAvatar();
		ThreadSupport.sleep(1000);
	}

}
