package grail;

import util.annotations.Tags;



@Tags({"AnimatingCommand"})
public class AnimatingCommand implements Runnable {

	Animator animator;
	
	public AnimatingCommand(BodyInterface anAvatar){
		animator = new AnAnimator(anAvatar);
	}
	
	public void run() {
		animator.animate();
		
	}

}
