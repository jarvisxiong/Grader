package mp.bean.commands;

import mp.ui.Avatar;
import util.annotations.Tags;

@Tags({"RotateLeftArmCommand"})
public class RotateRightArmCommand implements Runnable {
	
	Avatar avatar;
	int rotateDegrees;

	public RotateRightArmCommand(Avatar anAvatar, int rotation){
		avatar = anAvatar;
		rotateDegrees = rotation;
	}
	public void run() {
		avatar.getArms().getRightLine().rotate(rotateDegrees);
	}

}
