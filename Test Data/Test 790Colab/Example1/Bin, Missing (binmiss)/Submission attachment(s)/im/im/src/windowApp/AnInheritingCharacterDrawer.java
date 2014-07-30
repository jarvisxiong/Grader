package windowApp;


import java.awt.Frame;
import java.awt.Graphics;

import util.awt.ADelegateFrame;

public class AnInheritingCharacterDrawer extends Frame {
	final static int CARAT_LENGTH = 10; 
	ADelegateFrame delegateFrame;
	int charX, charY;
	char c;	
	
	public void paint ( Graphics g) {
		g.drawLine(charX, charY, charX, charY - CARAT_LENGTH);
		g.drawString("" + c, charX, charY);
	}	
}
