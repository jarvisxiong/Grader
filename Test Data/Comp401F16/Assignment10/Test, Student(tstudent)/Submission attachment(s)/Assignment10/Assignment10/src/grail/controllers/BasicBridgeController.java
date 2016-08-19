package grail.controllers;

import grail.avatar.Avatar;
import grail.paint.AObservablePainter;
import grail.paint.ObservablePainter;
import grail.scene.BridgeScene;
import util.annotations.Tags;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Tags({"BridgeSceneController"})
public class BasicBridgeController implements BridgeController{
	private BridgeScene aScene;
	private int clickedX, clickedY;
	private Avatar arthur,galahad,robin,lancelot;
	private ObservablePainter aModel;
	
	public BasicBridgeController(BridgeScene scene) {
		this(scene, new AObservablePainter());
	}
	
	public BasicBridgeController(BridgeScene scene, ObservablePainter model) {
		this.aScene = scene;
		setModel(model);
		this.clickedX = 0;
		this.clickedY = 0;
		this.arthur = scene.getArthur();
		this.galahad = scene.getGalahad();
		this.robin = scene.getRobin();
		this.lancelot = scene.getLancelot();
	}

	
	public void keyTyped(KeyEvent e) {
		char pressedKey = e.getKeyChar();
		processKeyInput(pressedKey);
	}
	
	public void setModel(ObservablePainter model) {
		this.aModel = model;
		((Component) model).addMouseListener(this);
		((Component) model).addKeyListener(this);
	}
	
	public ObservablePainter getModel() {return this.aModel;}

	
	public void keyPressed(KeyEvent e) {}

	
	public void keyReleased(KeyEvent e) {}

	
	public void mouseClicked(MouseEvent e) {
		this.clickedX = e.getX();
		this.clickedY = e.getY();
	}

	
	public void mousePressed(MouseEvent e) {}

	
	public void mouseReleased(MouseEvent e) {}

	
	public void mouseEntered(MouseEvent e) {}

	
	public void mouseExited(MouseEvent e) {}
	
	private void processKeyInput(char key) {
		if (key == 'a') {
			this.arthur.setLocation(clickedX, clickedY);
		} else if (key == 'l') {
			this.lancelot.setLocation(clickedX, clickedY);
		} else if (key == 'g') {
			this.galahad.setLocation(clickedX, clickedY);
		} else if (key == 'r') {
			this.robin.setLocation(clickedX, clickedY);
		} else if (key == 'o') {
			this.aScene.setOriginalPositions();
		}
	}

}
