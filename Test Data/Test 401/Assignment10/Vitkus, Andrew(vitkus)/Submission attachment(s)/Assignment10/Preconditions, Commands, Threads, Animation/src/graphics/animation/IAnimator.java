package graphics.animation;

import util.annotations.Tags;

@Tags({ "animator" })
public interface IAnimator {
	public Thread animate(IAnimatingCommand animatingCommand);
}
