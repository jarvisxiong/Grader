package mp.painter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.BridgeScene;
import util.annotations.Tags;

@Tags({"BridgeSceneController"})
public class ABridgeSceneController extends ABridgeScenePainter implements BridgeSceneController{
	protected int mouseX;
	protected int mouseY;
	
	protected BridgeScene scene;
	
	public ABridgeSceneController(BridgeScene scene){
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		this.scene = scene;
		
		addPaintListener(new AnAvatarPaintListener(scene.getArthur()));
		addPaintListener(new AnAvatarPaintListener(scene.getLancelot()));
		addPaintListener(new AnAvatarPaintListener(scene.getRobin()));
		addPaintListener(new AnAvatarPaintListener(scene.getGuard()));
		addPaintListener(new AnAvatarPaintListener(scene.getGalahad()));
		
		addPaintListener(new ABackgroundPaintListener(scene.getGorge()));
		
		addPaintListener(new AnAreaPaintListener(scene.getGuardArea()));
		addPaintListener(new AnAreaPaintListener(scene.getKnightArea()));
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A){
			scene.getArthur().getHead().setX(mouseX);
			scene.getArthur().getHead().setY(mouseY);
			scene.getArthur().syncShapes();
		}
		else if(e.getKeyCode() == KeyEvent.VK_G){
			scene.getGalahad().getHead().setX(mouseX);
			scene.getGalahad().getHead().setY(mouseY);
			scene.getGalahad().syncShapes();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L){
			scene.getLancelot().getHead().setX(mouseX);
			scene.getLancelot().getHead().setY(mouseY);
			scene.getLancelot().syncShapes();
		}
		else if(e.getKeyCode() == KeyEvent.VK_R){
			scene.getRobin().getHead().setX(mouseX);
			scene.getRobin().getHead().setY(mouseY);
			scene.getRobin().syncShapes();
		}
		else if(e.getKeyCode() == KeyEvent.VK_O){
			scene.reset();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
