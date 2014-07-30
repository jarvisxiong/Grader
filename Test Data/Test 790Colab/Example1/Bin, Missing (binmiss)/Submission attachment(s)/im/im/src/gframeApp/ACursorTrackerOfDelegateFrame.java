package gframeApp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.awt.AGraphicsFrame;

public class ACursorTrackerOfDelegateFrame implements MouseListener, KeyListener{
	protected AGraphicsFrame frame;
	protected int charX, charY;
	protected char c;		
	public ACursorTrackerOfDelegateFrame(AGraphicsFrame theFrame) {
		frame = theFrame;
		init();
	}
	public void init() {
		frame.addMouseListener(this);
		frame.addKeyListener(this);
	}	
	public void mousePressed(MouseEvent event) {
		charX = event.getX();
		charY = event.getY();
		//System.out.println("Mouse pressed");
	}
	public void keyTyped(KeyEvent event) {
		c = event.getKeyChar();		
	}	
	public void mouseEntered(MouseEvent event) {	}	
	public void mouseExited(MouseEvent event) {}	
	public void mouseClicked(MouseEvent event) {}	
	public void mouseReleased(MouseEvent event) {}	
	public void keyPressed(KeyEvent event) {}	
	public void keyReleased(KeyEvent event) {} 	
}
