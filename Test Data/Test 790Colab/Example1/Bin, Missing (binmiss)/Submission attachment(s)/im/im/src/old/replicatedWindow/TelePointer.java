package old.replicatedWindow;

import util.awt.ADelegateFrame;

public interface TelePointer {

	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);

	public ADelegateFrame getDelegateFrame();

	public void setDelegateFrame(ADelegateFrame theDelagateFrame);

}