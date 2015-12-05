package mp.commandobjects;

import mp.shapes.Avatar;
import util.annotations.Tags;

@Tags({"AnimatingCommand"})
public class AnimatingCommandObject implements Runnable{
	private Animator animator;
	private Avatar avatar;
	
	public AnimatingCommandObject(Animator animator, Avatar avatar){
		this.animator = animator;
		this.avatar = avatar;
	}
	
	@Override
	public void run() {
		animator.animateAvatar(avatar);
	}
	
}
