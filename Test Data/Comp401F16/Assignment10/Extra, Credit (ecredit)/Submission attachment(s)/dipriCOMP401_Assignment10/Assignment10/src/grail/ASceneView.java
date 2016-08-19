package grail;


import java.awt.Component;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
@Tags("InheritingBridgeScenePainter")

public class ASceneView extends Component implements PropertyChangeListener{
	BridgeScene aScene;
	public static final float MAGIC_FLOAT = 5f;
	BasicStroke dotted = new BasicStroke(MAGIC_FLOAT, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
            MAGIC_FLOAT, new float[] {2f}, 0f);
	
	public ASceneView(BridgeScene newScene){
		setFocusable(true);
		aScene = newScene;
		aScene.getArthur().getAHead().addPropertyChangeListener(this);
		aScene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		aScene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		aScene.getArthur().getBody().addPropertyChangeListener(this);
		aScene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		aScene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		aScene.getArthur().getText().addPropertyChangeListener(this);
		
		aScene.getRobin().getAHead().addPropertyChangeListener(this);
		aScene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		aScene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		aScene.getRobin().getBody().addPropertyChangeListener(this);
		aScene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		aScene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		aScene.getRobin().getText().addPropertyChangeListener(this);
		
		aScene.getGuard().getAHead().addPropertyChangeListener(this);
		aScene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		aScene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		aScene.getGuard().getBody().addPropertyChangeListener(this);
		aScene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		aScene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		aScene.getGuard().getText().addPropertyChangeListener(this);
		
		aScene.getLancelot().getAHead().addPropertyChangeListener(this);
		aScene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		aScene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		aScene.getLancelot().getBody().addPropertyChangeListener(this);
		aScene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		aScene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		aScene.getLancelot().getText().addPropertyChangeListener(this);
		
		aScene.getGalahad().getAHead().addPropertyChangeListener(this);
		aScene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		aScene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		aScene.getGalahad().getBody().addPropertyChangeListener(this);
		aScene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		aScene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		aScene.getGalahad().getText().addPropertyChangeListener(this);
		
		aScene.getBotBridge().addPropertyChangeListener(this);
		aScene.getTopBridge().addPropertyChangeListener(this);
		aScene.getGuardArea().addPropertyChangeListener(this);
		aScene.getKnightArea().addPropertyChangeListener(this);
		aScene.getLeftEdge().addPropertyChangeListener(this);
		aScene.getRightEdge().addPropertyChangeListener(this);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(MAGIC_FLOAT));
		draw(g2, aScene);
	}

	private void draw(Graphics2D g, BridgeScene aScene2) {
		draw(g, aScene2.getArthur().getArms().getLeftLine());
		draw(g, aScene2.getArthur().getArms().getRightLine());
		draw(g, aScene2.getArthur().getBody());
		draw(g, aScene2.getArthur().getLegs().getLeftLine());
		draw(g, aScene2.getArthur().getLegs().getRightLine());
		draw(g, aScene2.getArthur().getAHead());
		draw(g, aScene2.getArthur().getText());
		
		draw(g, aScene2.getGuard().getArms().getLeftLine());
		draw(g, aScene2.getGuard().getArms().getRightLine());
		draw(g, aScene2.getGuard().getBody());
		draw(g, aScene2.getGuard().getLegs().getLeftLine());
		draw(g, aScene2.getGuard().getLegs().getRightLine());
		draw(g, aScene2.getGuard().getAHead());
		draw(g, aScene2.getGuard().getText());
		
		draw(g, aScene2.getRobin().getArms().getLeftLine());
		draw(g, aScene2.getRobin().getArms().getRightLine());
		draw(g, aScene2.getRobin().getBody());
		draw(g, aScene2.getRobin().getLegs().getLeftLine());
		draw(g, aScene2.getRobin().getLegs().getRightLine());
		draw(g, aScene2.getRobin().getAHead());
		draw(g, aScene2.getRobin().getText());
		
		draw(g, aScene2.getLancelot().getArms().getLeftLine());
		draw(g, aScene2.getLancelot().getArms().getRightLine());
		draw(g, aScene2.getLancelot().getBody());
		draw(g, aScene2.getLancelot().getLegs().getLeftLine());
		draw(g, aScene2.getLancelot().getLegs().getRightLine());
		draw(g, aScene2.getLancelot().getAHead());
		draw(g, aScene2.getLancelot().getText());
		
		draw(g, aScene2.getGalahad().getArms().getLeftLine());
		draw(g, aScene2.getGalahad().getArms().getRightLine());
		draw(g, aScene2.getGalahad().getBody());
		draw(g, aScene2.getGalahad().getLegs().getLeftLine());
		draw(g, aScene2.getGalahad().getLegs().getRightLine());
		draw(g, aScene2.getGalahad().getAHead());
		draw(g, aScene2.getGalahad().getText());
		
		draw(g, aScene2.getBotBridge());
		draw(g, aScene2.getTopBridge());
		draw(g, aScene2.getLeftEdge());
		draw(g, aScene2.getRightEdge());
		draw(g, aScene2.getGuardArea());
		draw(g, aScene2.getKnightArea());
		
	}
    private void draw(Graphics2D g, StandingArea knightArea) {
		g.drawOval(knightArea.getX(), knightArea.getY(), knightArea.getWidth(), knightArea.getHeight());
		
	}

	private void draw(Graphics2D g, StringShape text) {
		String aString = text.getText();
		g.drawString(aString, text.getX(), text.getY());
		
	}

	private void draw(Graphics2D g, Head aHead) {
    	Image avatar = Toolkit.getDefaultToolkit().getImage(aHead.getImageFileName());
    	g.drawImage(avatar, aHead.getX(), aHead.getY(), this);
		
	}

	private void draw(Graphics2D g, Line aLine) {
		Line2D line = new Line2D.Double();
		line.setLine(aLine.getX(), aLine.getY(),
				aLine.getX() + aLine.getWidth(),
				aLine.getY() + aLine.getHeight());
		
		g.draw(line);
	}
	
public void addListener(PropertyListenerRegisterer aPropertyChangeRegister){
	aPropertyChangeRegister.addPropertyChangeListener(this);
}

@Override
public void propertyChange(PropertyChangeEvent evt) {
	repaint();	
}
	
}