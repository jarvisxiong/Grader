package mp.ui.mvc;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import mp.ui.AdvLocatable;
import mp.ui.AnAdvLocatable;
import mp.ui.Avatar;
import mp.ui.BridgeScene;
import util.annotations.Tags;

@Tags({"BridgeSceneController"})
public class ABridgeSceneController implements BridgeSceneController {
	
	BridgeScene scene;
	Component component;
	AdvLocatable click;
	Avatar currAvatar;
	
	public ABridgeSceneController(BridgeScene bridgescene, Component comp) {
		
		scene = bridgescene;
		component = comp;
		component.addMouseListener(this);
		component.addKeyListener(this);
		component.setFocusable(true);
		
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		switch(c) {
		case 'a':
			currAvatar = scene.getArthur();
			currAvatar.setLocation(click);
			break;
		case 'g':
			currAvatar = scene.getGalahad();
			currAvatar.setLocation(click);
			break;
		case 'l':
			currAvatar = scene.getLancelot();
			currAvatar.setLocation(click);
			break;
		case 'r':
			currAvatar = scene.getRobin();
			currAvatar.setLocation(click);
			break;
		case 'o':
			scene.resetLocation();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		click = new AnAdvLocatable();
		click.setX(e.getX());
		click.setY(e.getY());
		System.out.println("("+ e.getX() + ", " + e.getY() + ")");
	}
	public Avatar getCurrAvatar() {
		return currAvatar;
	}
	public BridgeScene getBridgeScene() {
		return scene;
	}

}
