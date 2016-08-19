package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.Avatar;
import mp.Circle;
import mp.Gorge;
import mp.Line;
import mp.Rectangle;
import mp.StringShape;
import mp.VShape;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"InheritingBridgeScenePainter"})
public class InheritingBridgeScenePainter extends Component implements PropertyChangeListener{
	
	BridgeSceneInterface aScene,scene;
	
	public InheritingBridgeScenePainter(BridgeSceneInterface theScene){
		aScene = theScene;
		scene = theScene;
		setFocusable(true);
		//Arthur------------------------------------------------------------------------------------------------
			aScene.getArthur().getText().addPropertyChangeListener(this);
			aScene.getArthur().getHead().addPropertyChangeListener(this);
			aScene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
			aScene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);				
			aScene.getArthur().getBody().addPropertyChangeListener(this);
			aScene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
			aScene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
				
		//Galahad------------------------------------------------------------------------------------------------
			aScene.getGalahad().getText().addPropertyChangeListener(this);
			aScene.getGalahad().getHead().addPropertyChangeListener(this);
			aScene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
			aScene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
			aScene.getGalahad().getBody().addPropertyChangeListener(this);
			aScene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
			aScene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		//Lancelot------------------------------------------------------------------------------------------------
			aScene.getLancelot().getText().addPropertyChangeListener(this);
			aScene.getLancelot().getHead().addPropertyChangeListener(this);
			aScene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
			aScene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
			aScene.getLancelot().getBody().addPropertyChangeListener(this);
			aScene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
			aScene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		//Robin------------------------------------------------------------------------------------------------
			aScene.getRobin().getText().addPropertyChangeListener(this);
			aScene.getRobin().getHead().addPropertyChangeListener(this);
			aScene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
			aScene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
			aScene.getRobin().getBody().addPropertyChangeListener(this);
			aScene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
			aScene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		//Guard------------------------------------------------------------------------------------------------
			aScene.getGuard().getText().addPropertyChangeListener(this);
			aScene.getGuard().getHead().addPropertyChangeListener(this);
			aScene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
			aScene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
			aScene.getGuard().getBody().addPropertyChangeListener(this);
			aScene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
			aScene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
			
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        draw(g2, aScene);
    }
	public void draw(Graphics2D g, BridgeSceneInterface scene){
		draw(g, scene.getArthur());
		draw(g, scene.getGalahad());
        draw(g, scene.getLancelot());
        draw(g, scene.getRobin());
        draw(g, scene.getGuard());
        draw(g, scene.getGorge());
        draw(g, scene.getGuardArea());
        draw(g, scene.getKnightArea());
	}
	
	public void draw(Graphics2D g, Avatar avatar){
		draw(g, avatar.getText());
		draw(g, avatar.getHead());
		draw(g, avatar.getArms());
		draw(g, avatar.getBody());
		draw(g, avatar.getLegs());
	}
	
	public void draw(Graphics2D g, StringShape text){
		String s = text.getText();
		g.drawString(s, text.getX(), text.getY());
	}
	
	public void draw(Graphics2D g, mp.Image image){
		Image img = Toolkit.getDefaultToolkit().getImage(image.getImageFileName());
        g.drawImage(img, image.getX(), image.getY(), this); 
	}
	
	public void draw(Graphics2D g, VShape shape){
		Line left = shape.getLeftLine();
		Line right = shape.getRightLine();
		g.drawLine(left.getX(), left.getY(), left.getX() - 25, left.getY() + 25);
		g.drawLine(right.getX(), right.getY(), right.getX() + 25, right.getY() + 25);
	}
	public void draw(Graphics2D g, Gorge aGorge){
		Gorge gorge = aGorge;
		Line left = gorge.getG1();
		Line right = gorge.getG2();
		Rectangle bridge = gorge.getBridge();
		g.drawLine(left.getX(), left.getY(), left.getX(), left.getY() + 800);
		g.drawLine(right.getX(), right.getY(), right.getX(), right.getY() + 800);
		g.drawRect(bridge.getX(),bridge.getY(), 100,50);
	}
	
	public void draw(Graphics2D g, Line line){
		Line body = line;
		g.drawLine(body.getX(), body.getY(), body.getX(), body.getY() + 50);
	}
	
	public void draw(Graphics2D g, Circle circle){
		Circle area = circle;
		g.drawOval(area.getX(), area.getY(), area.getWidth(), area.getHeight());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
		/*
		String current = "";
		if (arg0.getSource() == scene.getArthur().getText())
			current = "Arthur's Text";
		else if (arg0.getSource() == scene.getArthur().getHead())
			current = "Arthur's Head";
		else if (arg0.getSource() == scene.getArthur().getArms().getLeftLine())
			current = "Arthur's Left Arm";
		else if (arg0.getSource() == scene.getArthur().getArms().getRightLine())
			current = "Arthur's Right Arm";
		else if (arg0.getSource() == scene.getArthur().getBody())
			current = "Arthur's Body";
		else if (arg0.getSource() == scene.getArthur().getLegs().getLeftLine())
			current = "Arthur's Left Leg";
		else if (arg0.getSource() == scene.getArthur().getLegs().getRightLine())
			current = "Arthur's Right Leg";
		
		else if (arg0 == scene.getGalahad().getText())
			current = "Galahad's Text";
		else if (arg0.getSource() == scene.getGalahad().getHead())
			current = "Galahad's Head";
		else if (arg0.getSource() == scene.getGalahad().getArms().getLeftLine())
			current = "Galahad's Left Arm";
		else if (arg0.getSource() == scene.getGalahad().getArms().getRightLine())
			current = "Galahad's Right Arm";
		else if (arg0.getSource() == scene.getGalahad().getBody())
			current = "Galahad's Body";
		else if (arg0.getSource() == scene.getGalahad().getLegs().getLeftLine())
			current = "Galahad's Left Leg";
		else if (arg0.getSource() == scene.getGalahad().getLegs().getRightLine())
			current = "Galahad's Right Leg";
		
		else if (arg0 == scene.getLancelot().getText())
			current = "Lancelot's Text";
		else if (arg0.getSource() == scene.getLancelot().getHead())
			current = "Lancelot's Head";
		else if (arg0.getSource() == scene.getLancelot().getArms().getLeftLine())
			current = "Lancelot's Left Arm";
		else if (arg0.getSource() == scene.getLancelot().getArms().getRightLine())
			current = "Lancelot's Right Arm";
		else if (arg0.getSource() == scene.getLancelot().getBody())
			current = "Lancelot's Body";
		else if (arg0.getSource() == scene.getLancelot().getLegs().getLeftLine())
			current = "Lancelot's Left Leg";
		else if (arg0.getSource() == scene.getLancelot().getLegs().getRightLine())
			current = "Lancelot's Right Leg";
		
		else if (arg0 == scene.getRobin().getText())
			current = "Robin's Text";
		else if (arg0.getSource() == scene.getRobin().getHead())
			current = "Robin's Head";
		else if (arg0.getSource() == scene.getRobin().getArms().getLeftLine())
			current = "Robin's Left Arm";
		else if (arg0.getSource() == scene.getRobin().getArms().getRightLine())
			current = "Robin's Right Arm";
		else if (arg0.getSource() == scene.getRobin().getBody())
			current = "Robin's Body";
		else if (arg0.getSource() == scene.getRobin().getLegs().getLeftLine())
			current = "Robin's Left Leg";
		else if (arg0.getSource() == scene.getRobin().getLegs().getRightLine())
			current = "Robin's Right Leg";
		
		else if (arg0 == scene.getGuard().getText())
			current = "Guard's Text";
		else if (arg0.getSource() == scene.getGuard().getHead())
			current = "Guard's Head";
		else if (arg0.getSource() == scene.getGuard().getArms().getLeftLine())
			current = "Guard's Left Arm";
		else if (arg0.getSource() == scene.getGuard().getArms().getRightLine())
			current = "Guard's Right Arm";
		else if (arg0.getSource() == scene.getGuard().getBody())
			current = "Guard's Body";
		else if (arg0.getSource() == scene.getGuard().getLegs().getLeftLine())
			current = "Guard's Left Leg";
		else if (arg0.getSource() == scene.getGuard().getLegs().getRightLine())
			current = "Guard's Right Leg";
			
		System.out.println("Object: " + current + "\t Property name: "
				+ arg0.getPropertyName() + "\t old value: "
				+ arg0.getOldValue() + "\t new value: " + arg0.getNewValue());
		*/
	}
	
	public void register (PropertyListenerRegisterer aPropertyChangeRegister){
		aPropertyChangeRegister.addPropertyChangeListener(this);        
	}

}