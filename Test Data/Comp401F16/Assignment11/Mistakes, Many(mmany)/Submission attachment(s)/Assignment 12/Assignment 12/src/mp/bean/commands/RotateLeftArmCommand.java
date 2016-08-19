package mp.bean.commands;

import mp.ui.Avatar;
import util.annotations.Tags;

@Tags({"RotateLeftArmCommand"})
public class RotateLeftArmCommand implements Runnable {
	
	Avatar avatar;
	int rotateDegrees;

	public RotateLeftArmCommand(Avatar anAvatar, int rotation){
		avatar = anAvatar;
		rotateDegrees = rotation;
	}
	public void run() {
		avatar.getArms().getLeftLine().rotate(rotateDegrees);
	}

}
