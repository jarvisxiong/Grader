package windowApp;


import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ACursorTracker implements MouseListener, KeyListener{
	Frame frame;
	int charX, charY;
	char lastChar;		
	public ACursorTracker(Frame theFrame) {
		frame = theFrame;
		frame.addMouseListener(this);
		frame.addKeyListener(this);
	}		
	public void mousePressed(MouseEvent event) {
		charX = event.getX();
		charY = event.getY();
		frame.repaint();
	}
	public void keyTyped(KeyEvent event) {
		lastChar = event.getKeyChar();
		frame.repaint();		
	}	
	public void mouseEntered(MouseEvent event) {	}	
	public void mouseExited(MouseEvent event) {}	
	public void mouseClicked(MouseEvent event) {}	
	public void mouseReleased(MouseEvent event) {}	
	public void keyPressed(KeyEvent event) {}	
	public void keyReleased(KeyEvent event) {} 
	
}
