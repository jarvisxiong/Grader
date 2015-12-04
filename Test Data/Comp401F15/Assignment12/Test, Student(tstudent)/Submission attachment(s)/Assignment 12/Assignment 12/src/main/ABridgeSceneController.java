package main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import mp.BridgeScene;
import util.annotations.Tags;
@Tags({"BridgeSceneController"})
public class ABridgeSceneController implements BridgeSceneController{
	ABridgeScenePainter theBridgeScenePainter; 
	BridgeScene theBridgeScene;
	int x, y;
	
	public ABridgeSceneController (ABridgeScenePainter aBridgeScenePainter, BridgeScene aBridgeScene) {
		theBridgeScenePainter = aBridgeScenePainter;
		theBridgeScene = aBridgeScene;
		aBridgeScenePainter.addMouseListener(this);
		aBridgeScenePainter.addKeyListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void keyTyped(KeyEvent e) {
		Character key = e.getKeyChar();
		if (key == 'a') {theBridgeScene.getArthur().relocate(x, y);}
		if (key == 'g') {theBridgeScene.getGalahad().relocate(x, y);}
		if (key == 'r') {theBridgeScene.getRobin().relocate(x, y);}
		if (key == 'l') {theBridgeScene.getLancelot().relocate(x, y);}
		if (key == 'o') {
			theBridgeScene.getArthur().reset();
			theBridgeScene.getGalahad().reset();
			theBridgeScene.getRobin().reset();
			theBridgeScene.getLancelot().reset();
		}	
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}


}
