package graphics.animation;

import graphics.avatar.IAvatar;

import util.annotations.Tags;

@Tags({ "animating command" })
public class ClapAnimatingCommand implements IClapAnimatingCommand {
	private IAvatar avatar;
	private int count;

	
	private final static int DELAY = 50;
			
	public ClapAnimatingCommand(IAvatar avatar, int count) {
		this.avatar = avatar;
		this.count = count;
	}

	@Override
	public void run() {
		for(int i = 0; i < count; i ++) {

			int dir = 1;
			for(int j = 0; j < 8; j ++) {
				avatar.rotateRightArm(dir);
				avatar.rotateLeftArm(-dir);
				sleep(DELAY);
			}
			dir *= -1;
			for(int j = 0; j < 8; j ++) {
				avatar.rotateRightArm(dir);
				avatar.rotateLeftArm(-dir);
				sleep(DELAY);
			}
		}
	}


	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
