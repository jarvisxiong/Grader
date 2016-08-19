package grail;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.annotations.Tags;

@Tags({"BridgeSceneController"})
public class ABridgeSceneController implements BridgeSceneController {
BridgeScene aScene;
Component aView;
final static int A = 65;
final static int G = 71;
final static int L = 76;
final static int R = 82;
final static int O = 79;
final static int ARTHURX = 130;
final static int KNIGHT_Y = 150;
final static int ROBIN_X = 180;
final static int GALAHAD_X = 230;
final static int LANCELOT_X = 280;



int x;
int y;

public ABridgeSceneController(BridgeScene scene, Component painter){
	aScene = scene;
	aView = painter;
	aView.addMouseListener(this);
	aView.addKeyListener(this);
	
}

public void mouseClicked(MouseEvent click) {
	setX(click.getX());
	setY(click.getY());
}

public void mouseEntered(MouseEvent arg0) {}

public void mouseExited(MouseEvent arg0) {}

public void mousePressed(MouseEvent arg0) {}

public void mouseReleased(MouseEvent arg0) {}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

public void setX(int newX) {
	x = newX;
}

public void setY(int newY) {
	y = newY;
}

@Override
public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();      // a = 65; g = 71;  l = 76;   r = 82;   o = 79
	if(keyCode == A){
		aScene.getArthur().moveBody(x-aScene.getArthur().getBody().getX(), y-aScene.getArthur().getBody().getY());
	}
	else if(keyCode == G){
		aScene.getGalahad().moveBody(x-aScene.getGalahad().getBody().getX(), y-aScene.getGalahad().getBody().getY());
	}
	else if(keyCode == L){
		aScene.getLancelot().moveBody(x-aScene.getLancelot().getBody().getX(), y-aScene.getLancelot().getBody().getY());
	}
	else if(keyCode == R){
		aScene.getRobin().moveBody(x-aScene.getRobin().getBody().getX(), y-aScene.getRobin().getBody().getY());
	}
	else if(keyCode == O){
		aScene.getArthur().moveBody(ARTHURX-aScene.getArthur().getBody().getX(), KNIGHT_Y-aScene.getArthur().getBody().getY());
		aScene.getGalahad().moveBody(GALAHAD_X-aScene.getGalahad().getBody().getX(), KNIGHT_Y-aScene.getGalahad().getBody().getY());
		aScene.getLancelot().moveBody(LANCELOT_X-aScene.getLancelot().getBody().getX(), KNIGHT_Y-aScene.getLancelot().getBody().getY());
		aScene.getRobin().moveBody(ROBIN_X-aScene.getRobin().getBody().getX(), KNIGHT_Y-aScene.getRobin().getBody().getY());
	}
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


}
