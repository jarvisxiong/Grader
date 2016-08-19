package grail;

import util.annotations.Tags;
import util.misc.ThreadSupport;


@Tags({"Animator"})
public class AnAnimator implements Animator{

	BodyInterface avatar;
	final static int X_ROTATES = 100;
	
	public AnAnimator(BodyInterface anAvatar){
		avatar = anAvatar;
	}
	
	@Tags({"animateAvatar"})
	public void animate(){
		for (int x = 0; x<X_ROTATES; x++){
			avatar.getArms().getRightLine().rotate(x);
			ThreadSupport.sleep(X_ROTATES);
		}
	}

}
