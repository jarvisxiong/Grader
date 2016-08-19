package mp.Scene;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import util.annotations.Tags;

@Tags({"BridgeSceneController"})
public class ABridgeSceneController implements BridgeSceneController{
	
	BridgeScene aScene;
	Component aView;
	final int thirty=30;
	final int onethirty=130;
	final int twothirty=230;
	final int threethirty=330;
	int mouseX=0;
	int mouseY=0;
	public ABridgeSceneController(BridgeScene newScene){}
	public ABridgeSceneController(BridgeScene newScene, Component newComponent){
		aView = newComponent;
		aScene = newScene;
		aView.addKeyListener(this);
		aView.addMouseListener(this);
		aView.setFocusable(true);
	}
	
	public void keyTyped(KeyEvent e){
		char typedChar = e.getKeyChar();

    	switch (typedChar) {
    	case 'a' :
    		aScene.getArthur().flashThis(mouseX, mouseY);
    		break;
    	case 'g':
    		aScene.getGalahad().flashThis(mouseX, mouseY);
    		break;
    	case 'l':
    		aScene.getLancelot().flashThis(mouseX, mouseY);
    		break;
    	case 'r':
    		aScene.getRobin().flashThis(mouseX, mouseY);
    		break;
    	case 'o':
    		aScene.getArthur().flashThis(thirty,thirty);
    		aScene.getGalahad().flashThis(onethirty,thirty);
    		aScene.getLancelot().flashThis(twothirty,thirty);
    		aScene.getRobin().flashThis(threethirty,thirty);
    		break;
    	}
	}
	public void mouseClicked(MouseEvent e){
//		aScene.getArthur().flashThis(e.getX(), e.getY());
//		aScene.getArthur().getArms().getLeftLine().setX(e.getX());
		mouseX=e.getX();
		mouseY=e.getY();
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

}
