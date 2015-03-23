package old.replicatedWindow;

import util.awt.ADelegateFrame;

public class ATelePointer implements TelePointer {
	int x, y;
	ADelegateFrame delegateFrame;
	public ATelePointer (ADelegateFrame theDelegateFrame, int theX, int theY) {
		delegateFrame = theDelegateFrame;
		x = theX;
		y = theY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ADelegateFrame getDelegateFrame() {
		return delegateFrame;
	}
	public void setDelegateFrame(ADelegateFrame theDelegateFrame) {
		delegateFrame = theDelegateFrame;
	}

}
