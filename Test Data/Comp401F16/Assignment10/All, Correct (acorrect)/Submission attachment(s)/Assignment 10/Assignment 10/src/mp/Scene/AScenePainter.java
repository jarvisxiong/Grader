package mp.Scene;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"InheritingBridgeScenePainter"})
public class AScenePainter extends Component implements ScenePainter{
	BridgeScene model;
	BasicStroke normal= new BasicStroke();
	public AScenePainter(BridgeScene aModel){
		model=aModel;
		TheBridgeScene.addPropertyChangeListener(model, this);
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(normal);
        g.setColor(Color.BLACK);     
        draw(g2, model);
    }
	 public void draw(Graphics2D g, BridgeScene scene) {
	    	draw(g, scene.getArthur());
	        draw(g, scene.getGalahad());
	        draw(g, scene.getGorge());
	        draw(g, scene.getGuard());
	        draw(g,scene.getGuardArea());
	        draw(g,scene.getKnightArea());
	        draw(g,scene.getLancelot());
	        draw(g,scene.getRobin());
	    }
	 public void draw(Graphics2D g,BoundedShape bs){
		 BoundedShape xx=bs;
		 g.drawOval(xx.getX(),xx.getY(), xx.getWidth(), xx.getWidth());
	 }
	 public void draw(Graphics2D g,Gorge aGorge){
		 Gorge gg=aGorge;
		 g.drawLine(gg.getG1().getX(), gg.getG1().getY(), gg.getG1().getX()+gg.getG1().getWidth(), 
				 gg.getG1().getY()+gg.getG1().getHeight());
		 g.drawLine(gg.getG2().getX(), gg.getG2().getY(), gg.getG2().getX()+gg.getG2().getWidth(), 
				 gg.getG2().getY()+gg.getG2().getHeight());
		 draw(g, gg.getBeidge());
	 }
	 public void draw(Graphics2D g,Avatar ava){
		  
		  g.drawLine(ava.getArms().getLeftLine().getX(), ava.getArms().getLeftLine().getY(), 
				  ava.getArms().getLeftLine().getX()+ava.getArms().getLeftLine().getWidth(), 
				  ava.getArms().getLeftLine().getY()+ava.getArms().getLeftLine().getHeight());
		  g.drawLine(ava.getArms().getRightLine().getX(), ava.getArms().getRightLine().getY(), 
				  ava.getArms().getRightLine().getX()+ava.getArms().getRightLine().getWidth(), 
				  ava.getArms().getRightLine().getY()+ava.getArms().getRightLine().getHeight());
		  g.drawLine(ava.getLegs().getLeftLine().getX(), ava.getLegs().getLeftLine().getY(), 
				  ava.getLegs().getLeftLine().getX()+ava.getLegs().getLeftLine().getWidth(), 
				  ava.getLegs().getLeftLine().getY()+ava.getLegs().getLeftLine().getHeight());
		  g.drawLine(ava.getLegs().getRightLine().getX(), ava.getLegs().getRightLine().getY(), 
				  ava.getLegs().getRightLine().getX()+ava.getLegs().getRightLine().getWidth(), 
				  ava.getLegs().getRightLine().getY()+ava.getLegs().getRightLine().getHeight());
		  g.drawLine(ava.getTorso().getX(), ava.getTorso().getY(), 
				  ava.getTorso().getX(), ava.getTorso().getY()+ava.getTorso().getHeight());
		  draw(g,ava.getHead());
		  draw(g,ava.getText());
	  }
	    public void draw(Graphics g, StringShape aLabel) {
	        String s = aLabel.getText();
	        g.drawString(s, aLabel.getX(), aLabel.getY());      
	    }
	    
	    public  void draw(Graphics2D g, ImageShape anImage) {
	        Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
	        g.drawImage(img, anImage.getX(), anImage.getY(), this);     
	    }
	    
		
		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			repaint();
		}
		public void register(PropertyListenerRegisterer aChange){
			aChange.addPropertyChangeListener(this);
		}

}
