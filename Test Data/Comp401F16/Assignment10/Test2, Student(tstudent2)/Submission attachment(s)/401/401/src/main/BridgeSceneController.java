package main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import mp.ALine;
import mp.Avatar;
import util.annotations.Tags;

@Tags({"BridgeSceneController"})
public class BridgeSceneController extends InheritingBridgeScenePainter implements BridgeSceneMouseListener, BridgeSceneKeyListener {
	static BridgeSceneInterface newScene;
	InheritingBridgeScenePainter aView;
	int oldX, oldY;
	int x = 0;
	int y = 0;
	int ArthurX,ArthurY, GalahadX, GalahadY, LancelotX, LancelotY, RobinX, RobinY;
	
	public BridgeSceneController(BridgeSceneInterface scene, InheritingBridgeScenePainter view){
		super(scene);
		newScene = scene;
		ArthurX = newScene.getArthur().getArms().getX();
		ArthurY = newScene.getArthur().getArms().getY();
		GalahadX = newScene.getGalahad().getArms().getX();
		GalahadY = newScene.getGalahad().getArms().getY();
		LancelotX = newScene.getLancelot().getArms().getX();
		LancelotY = newScene.getLancelot().getArms().getY();
		RobinX = newScene.getRobin().getArms().getX();
		RobinY = newScene.getRobin().getArms().getY();
		aView = view;
		aView.addMouseListener(this);
		aView.addKeyListener(this);
	}
	public void mouseClicked(MouseEvent e) {
		oldX = x;
		oldY = y;
		x = e.getX();
		y = e.getY();
		System.out.println(x);
		System.out.println(y);
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A){
			setAvatar(newScene.getArthur(),x,y);
		}
		if(e.getKeyCode() == KeyEvent.VK_G){
			setAvatar(newScene.getGalahad(),x,y); 
		}
		if(e.getKeyCode() == KeyEvent.VK_L){
			setAvatar(newScene.getLancelot(),x,y);
		}
		if(e.getKeyCode() == KeyEvent.VK_R){
			setAvatar(newScene.getRobin(),x,y);
		}
		if(e.getKeyCode() == KeyEvent.VK_O){
			setAvatar(newScene.getArthur(),ArthurX,ArthurY);
			setAvatar(newScene.getGalahad(),GalahadX,GalahadY);
			setAvatar(newScene.getLancelot(),LancelotX,LancelotY);
			setAvatar(newScene.getRobin(),RobinX,RobinY);
		}
	}
	public void keyReleased(KeyEvent e) {}
	
	public void setAvatar(Avatar avatar, int newX, int newY){
		//avatar.preciseMove(1, 0);
		//aView.addPropertyChangeListener(this);
		
		if(newX > avatar.getArms().getLeftLine().getX()){
			for(int i = avatar.getArms().getLeftLine().getX(); i < newX; i++){
				avatar.preciseMove(1, 0);
			}
		}
		if(newX < avatar.getArms().getLeftLine().getX()){
			for(int i = avatar.getArms().getLeftLine().getX(); newX < i; i--){
				avatar.preciseMove(-1, 0);
				}
		}
		if(newY > avatar.getArms().getLeftLine().getY()){
			for(int i = avatar.getArms().getLeftLine().getY(); i < newY; i++){
				avatar.preciseMove(0, 1);
			}
		}
		if(newY < avatar.getArms().getLeftLine().getY()){
			for(int i = avatar.getArms().getLeftLine().getY(); newY < i; i--){
				avatar.preciseMove(0, -1);
			}
		}
	}
}
