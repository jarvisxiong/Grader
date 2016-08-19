package main;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import util.annotations.Tags;
import mp.BridgeScene;
import mp.ImageShape;
import mp.Line;
import mp.OvalShape;
import mp.StringShape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
//import util.models.PropertyListenerRegisterer;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
@Tags({"InheritingBridgeScenePainter"})

public class ABridgeScenePainter extends Component implements BridgeScenePainter{
	
	/*
	 * I don't know why I have this but Eclipse was unhappy unless I did it.	 
	 */
	private static final long serialVersionUID = -459299669093812470L;
	BridgeScene theBridgeScene;
	
	public ABridgeScenePainter (BridgeScene aBridgeScene) {
		theBridgeScene = aBridgeScene;
		setFocusable(true);
		
		aBridgeScene.addPropertyChangeListener(this);
		
		aBridgeScene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getHead().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getSword().getSword().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getText().addPropertyChangeListener(this);
		aBridgeScene.getArthur().getTorso().getTorso().addPropertyChangeListener(this);
		
		aBridgeScene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getHead().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getSword().getSword().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getText().addPropertyChangeListener(this);
		aBridgeScene.getGalahad().getTorso().getTorso().addPropertyChangeListener(this);
		
		aBridgeScene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getHead().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getSword().getSword().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getText().addPropertyChangeListener(this);
		aBridgeScene.getGuard().getTorso().getTorso().addPropertyChangeListener(this);
		
		aBridgeScene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getHead().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getSword().getSword().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getText().addPropertyChangeListener(this);
		aBridgeScene.getLancelot().getTorso().getTorso().addPropertyChangeListener(this);
		
		aBridgeScene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getHead().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getSword().getSword().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getText().addPropertyChangeListener(this);
		aBridgeScene.getRobin().getTorso().getTorso().addPropertyChangeListener(this);
		
		aBridgeScene.getGorge().getLeftBank().addPropertyChangeListener(this);
		aBridgeScene.getGorge().getRightBank().addPropertyChangeListener(this);
		aBridgeScene.getGorge().getTopBridge().addPropertyChangeListener(this);
		aBridgeScene.getGorge().getBottomBridge().addPropertyChangeListener(this);
		
		aBridgeScene.getGuardArea().addPropertyChangeListener(this);
		aBridgeScene.getKnightArea().addPropertyChangeListener(this);
	}
	public void propertyChange(PropertyChangeEvent event) {
		repaint();
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		draw(g2, theBridgeScene);
	}
	
	public void draw (Graphics2D g, BridgeScene aBridgeScene) {
		draw(g, aBridgeScene.getArthur().getArms().getLeftLine());
		draw(g, aBridgeScene.getArthur().getArms().getRightLine());
		draw(g, aBridgeScene.getArthur().getLegs().getLeftLine());
		draw(g, aBridgeScene.getArthur().getLegs().getRightLine());
		draw(g, aBridgeScene.getArthur().getTorso().getTorso());
		draw(g, aBridgeScene.getArthur().getSword().getSword());
		draw(g, aBridgeScene.getArthur().getText());
		draw(g, aBridgeScene.getArthur().getHead().getHead());
		
		draw(g, aBridgeScene.getLancelot().getArms().getLeftLine());
		draw(g, aBridgeScene.getLancelot().getArms().getRightLine());
		draw(g, aBridgeScene.getLancelot().getLegs().getLeftLine());
		draw(g, aBridgeScene.getLancelot().getLegs().getRightLine());
		draw(g, aBridgeScene.getLancelot().getTorso().getTorso());
		draw(g, aBridgeScene.getLancelot().getSword().getSword());
		draw(g, aBridgeScene.getLancelot().getText());
		draw(g, aBridgeScene.getLancelot().getHead().getHead());
		
		draw(g, aBridgeScene.getRobin().getArms().getLeftLine());
		draw(g, aBridgeScene.getRobin().getArms().getRightLine());
		draw(g, aBridgeScene.getRobin().getLegs().getLeftLine());
		draw(g, aBridgeScene.getRobin().getLegs().getRightLine());
		draw(g, aBridgeScene.getRobin().getTorso().getTorso());
		draw(g, aBridgeScene.getRobin().getSword().getSword());
		draw(g, aBridgeScene.getRobin().getText());
		draw(g, aBridgeScene.getRobin().getHead().getHead());
		
		draw(g, aBridgeScene.getGuard().getArms().getLeftLine());
		draw(g, aBridgeScene.getGuard().getArms().getRightLine());
		draw(g, aBridgeScene.getGuard().getLegs().getLeftLine());
		draw(g, aBridgeScene.getGuard().getLegs().getRightLine());
		draw(g, aBridgeScene.getGuard().getTorso().getTorso());
		draw(g, aBridgeScene.getGuard().getSword().getSword());
		draw(g, aBridgeScene.getGuard().getText());
		draw(g, aBridgeScene.getGuard().getHead().getHead());
		
		draw(g, aBridgeScene.getGalahad().getArms().getLeftLine());
		draw(g, aBridgeScene.getGalahad().getArms().getRightLine());
		draw(g, aBridgeScene.getGalahad().getLegs().getLeftLine());
		draw(g, aBridgeScene.getGalahad().getLegs().getRightLine());
		draw(g, aBridgeScene.getGalahad().getTorso().getTorso());
		draw(g, aBridgeScene.getGalahad().getSword().getSword());
		draw(g, aBridgeScene.getGalahad().getText());
		draw(g, aBridgeScene.getGalahad().getHead().getHead());
		
		draw(g, aBridgeScene.getGuardArea());
		draw(g, aBridgeScene.getKnightArea());
		
		draw(g, aBridgeScene.getGorge().getBottomBridge());
		draw(g, aBridgeScene.getGorge().getLeftBank());
		draw(g, aBridgeScene.getGorge().getTopBridge());
		draw(g, aBridgeScene.getGorge().getRightBank());
	}
	
	public void draw (Graphics2D g, Line lineShape) {
		int x1 = lineShape.getX();
		int y1 = lineShape.getY();
		int x2 = lineShape.getX()+lineShape.getWidth();
		int y2 = lineShape.getY()+lineShape.getHeight();
		g.drawLine(x1, y1, x2, y2);
	}
	
	public void draw (Graphics2D g, ImageShape anImage) {
        Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
        g.drawImage(img, anImage.getX(), anImage.getY(), anImage.getWidth(), anImage.getHeight(), this);
	}
	
	public void draw (Graphics g, StringShape text) {
		String s = text.getText();
		int x = text.getX();
		int y = text.getY();
		g.drawString(s, x, y);
	}
	
	public void draw (Graphics2D g, OvalShape oval) {
		int x = oval.getX();
		int y = oval.getY();
		int width = oval.getWidth();
		int height = oval.getHeight();
		g.drawOval(x, y, width, height);
	}
	
	
}
