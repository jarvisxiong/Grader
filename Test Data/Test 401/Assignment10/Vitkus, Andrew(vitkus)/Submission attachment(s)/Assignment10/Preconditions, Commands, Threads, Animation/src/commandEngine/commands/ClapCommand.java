package commandEngine.commands;

import util.annotations.Tags;
import graphics.avatar.IAvatar;

@Tags({ "Clap Command" })
public class ClapCommand implements IClapCommand {
	private IAvatar avatar;
	private int claps;
	
	public ClapCommand(IAvatar avatar, int claps) {
		this.avatar = avatar;
		this.claps = claps;
	}

	@Override
	public void run() {
		Thread right;
		Thread left;
		int dir = 1;
		for(int i = 0; i < claps; i ++) {
			//System.out.println(i);
			
			right = new Thread(new RotateRightArmCommand(avatar, 19 * dir));
			left = new Thread(new RotateLeftArmCommand(avatar, -19 * dir));

			right.start();
			left.start();
			
			try {
				right.join();
				left.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			dir *= -1;
		}
	}

}
