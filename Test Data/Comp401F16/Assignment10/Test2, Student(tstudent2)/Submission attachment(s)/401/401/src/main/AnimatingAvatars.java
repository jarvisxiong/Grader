package main;

import mp.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"Animator"})
public class AnimatingAvatars implements Animator{
	
	Avatar avatar;
	
	public AnimatingAvatars(Avatar newAvatar){
		avatar = newAvatar;
	}
	@Tags({"animateAvatar"})
	public void animateAvatar() {
		avatar.animatedMove(100, 100);
		ThreadSupport.sleep(1000);
	}

}
