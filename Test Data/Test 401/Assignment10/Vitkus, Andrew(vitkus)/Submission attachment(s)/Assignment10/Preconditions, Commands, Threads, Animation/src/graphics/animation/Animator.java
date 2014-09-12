package graphics.animation;

import util.annotations.Tags;

@Tags({ "animator" })
public class Animator implements IAnimator {

	public Animator() {}

	@Override
	public Thread animate(IAnimatingCommand animatingCommand) {
		Thread t = new Thread(animatingCommand);
		t.start();
		return t;
	}

}
