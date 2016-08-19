package grail.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.annotations.Tags;
import grail.shape.BridgeSceneInterface;
import grail.view.ASceneView;
import grail.view.SceneView;
@Tags("BridgeSceneController")
public class ABridgeSceneController implements BridgeSceneController{
	BridgeSceneInterface aScene;
	ASceneView aView;
	int x,y;//current position
	int arthurX,arthurY;//to record the original location of the avatar
	int robinX,robinY;
	int galahadX,galahadY;
	int lancelotX, lancelotY;
	public ABridgeSceneController(BridgeSceneInterface scene, ASceneView sView)
	{
		aScene=scene;
		aView=sView;
		aView.addMouseListener(this);
		aView.addKeyListener(this);
		aView.setFocusable(true);
		robinX=aScene.getRobin().getAvatarLocation().getX();
		robinY=aScene.getRobin().getAvatarLocation().getY();
		arthurX=aScene.getArthur().getAvatarLocation().getX();
		arthurY=aScene.getArthur().getAvatarLocation().getY();
		galahadX=aScene.getGalahad().getAvatarLocation().getX();
	    galahadY=aScene.getGalahad().getAvatarLocation().getY();
	    lancelotX=aScene.getLancelot().getAvatarLocation().getX();
	    lancelotY=aScene.getLancelot().getAvatarLocation().getY();
	}
	
	public ABridgeSceneController(BridgeSceneInterface scene)
	{
		aScene=scene;
		aView.addMouseListener(this);
		robinX=aScene.getRobin().getAvatarLocation().getX();
		robinY=aScene.getRobin().getAvatarLocation().getY();
		arthurX=aScene.getArthur().getAvatarLocation().getX();
		arthurY=aScene.getArthur().getAvatarLocation().getY();
		galahadX=aScene.getGalahad().getAvatarLocation().getX();
	    galahadY=aScene.getGalahad().getAvatarLocation().getY();
	    lancelotX=aScene.getLancelot().getAvatarLocation().getX();
	    lancelotY=aScene.getLancelot().getAvatarLocation().getY();
	}
	
	 public void mouseClicked(MouseEvent e) {
	        ///aScene.getThief().setX(e.getX());
	        ///aScene.getThief().setY(e.getY());
		    x=e.getX();
		    y=e.getY();
		    
	    }    
	 public void keyPressed(KeyEvent e)
	 {  
	        char c=e.getKeyChar();  
	        int dx,dy;
	        switch (c)
	        {
		    	case 'o':
		    		//return to original location
		    		dx=arthurX-aScene.getArthur().getAvatarLocation().getX();
	        		dy=arthurY-aScene.getArthur().getAvatarLocation().getY();
	        		aScene.getArthur().move(dx, dy);
	        		
	        		dx=galahadX-aScene.getGalahad().getAvatarLocation().getX();
	        		dy=galahadY-aScene.getGalahad().getAvatarLocation().getY();
	        		aScene.getGalahad().move(dx, dy);
	        		
	        		dx=lancelotX-aScene.getLancelot().getAvatarLocation().getX();
	        		dy=lancelotY-aScene.getLancelot().getAvatarLocation().getY();
	        		aScene.getLancelot().move(dx, dy);
	        		
	        		dx=robinX-aScene.getRobin().getAvatarLocation().getX();
	        		dy=robinY-aScene.getRobin().getAvatarLocation().getY();
	        		aScene.getRobin().move(dx, dy);
		    		break;
	        	case 'a':
	        		//Arthur move to x,y
	        		dx=x-aScene.getArthur().getAvatarLocation().getX();
	        		dy=y-aScene.getArthur().getAvatarLocation().getY();
	        		aScene.getArthur().move(dx, dy);
	        		break;
	        	case 'g':
	        			
	        		dx=x-aScene.getGalahad().getAvatarLocation().getX();
	        		dy=y-aScene.getGalahad().getAvatarLocation().getY();
	        		aScene.getGalahad().move(dx, dy);
	        		break;
	        	case 'l':
	        		dx=x-aScene.getLancelot().getAvatarLocation().getX();
	        		dy=y-aScene.getLancelot().getAvatarLocation().getY();
	        		aScene.getLancelot().move(dx, dy);
	        		break;
	        	case 'r':
	        		dx=x-aScene.getRobin().getAvatarLocation().getX();
	        		dy=y-aScene.getRobin().getAvatarLocation().getY();
	        		aScene.getRobin().move(dx, dy);
	        		break;
        		default:
        			System.out.println("Invalid input:"+c);
        			break;
	        }
	        
	 }  
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}  
    public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		 char c=e.getKeyChar();  
	        int dx,dy;
	        switch (c)
	        {
		    	case 'o':
		    		//return to original location
		    		dx=arthurX-aScene.getArthur().getAvatarLocation().getX();
	        		dy=arthurY-aScene.getArthur().getAvatarLocation().getY();
	        		aScene.getArthur().move(dx, dy);
	        		
	        		dx=galahadX-aScene.getGalahad().getAvatarLocation().getX();
	        		dy=galahadY-aScene.getGalahad().getAvatarLocation().getY();
	        		aScene.getGalahad().move(dx, dy);
	        		
	        		dx=lancelotX-aScene.getLancelot().getAvatarLocation().getX();
	        		dy=lancelotY-aScene.getLancelot().getAvatarLocation().getY();
	        		aScene.getLancelot().move(dx, dy);
	        		
	        		dx=robinX-aScene.getRobin().getAvatarLocation().getX();
	        		dy=robinY-aScene.getRobin().getAvatarLocation().getY();
	        		aScene.getRobin().move(dx, dy);
		    		break;
	        	case 'a':
	        		//Arthur move to x,y
	        		dx=x-aScene.getArthur().getAvatarLocation().getX();
	        		dy=y-aScene.getArthur().getAvatarLocation().getY();
	        		aScene.getArthur().move(dx, dy);
	        		break;
	        	case 'g':
	        			
	        		dx=x-aScene.getGalahad().getAvatarLocation().getX();
	        		dy=y-aScene.getGalahad().getAvatarLocation().getY();
	        		aScene.getGalahad().move(dx, dy);
	        		break;
	        	case 'l':
	        		dx=x-aScene.getLancelot().getAvatarLocation().getX();
	        		dy=y-aScene.getLancelot().getAvatarLocation().getY();
	        		aScene.getLancelot().move(dx, dy);
	        		break;
	        	case 'r':
	        		dx=x-aScene.getRobin().getAvatarLocation().getX();
	        		dy=y-aScene.getRobin().getAvatarLocation().getY();
	        		aScene.getRobin().move(dx, dy);
	        		break;
     		default:
     			System.out.println("Invalid input:"+c);
     			break;
	        }
		
	};
}
