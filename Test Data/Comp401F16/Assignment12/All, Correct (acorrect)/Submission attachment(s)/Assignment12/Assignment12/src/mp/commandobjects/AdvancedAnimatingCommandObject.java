package mp.commandobjects;

import mp.helpers.BroadcastingClearanceManager;
import mp.shapes.Avatar;

public class AdvancedAnimatingCommandObject extends AnimatingCommandObject{
	protected BroadcastingClearanceManager manager;
	
	public AdvancedAnimatingCommandObject(Animator animator, Avatar avatar, BroadcastingClearanceManager manager) {
		super(animator, avatar);
		this.manager = manager;
	}
	
	@Override
	public void run(){
		manager.waitForProceed();
		animator.animateAvatar(avatar);
	}

}
