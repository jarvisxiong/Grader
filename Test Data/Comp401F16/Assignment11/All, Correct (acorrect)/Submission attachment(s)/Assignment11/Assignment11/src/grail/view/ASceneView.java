package grail.view;

import java.awt.Component;

import grail.shape.BoundedShape;
import grail.shape.BridgeSceneInterface;
import grail.shape.ImageShape;
import grail.shape.Line;
import grail.shape.StandArea;
import grail.shape.StringShape;

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
@Tags("InheritingBridgeScenePainter")

public class ASceneView extends Component implements PropertyChangeListener{




		BridgeSceneInterface aScene;

		public ASceneView(BridgeSceneInterface theScene) {
			aScene = theScene;
			aScene.addPropertyChangeListener(this);

			// Register with Arthur's body parts
			aScene.getArthur().getHead().addPropertyChangeListener(this);
			aScene.getArthur().getWaist().addPropertyChangeListener(this);
			aScene.getArthur().getArms().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getArthur().getArms().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getArthur().getLegs().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getArthur().getLegs().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getArthur().getText().addPropertyChangeListener(this);
			// Register with Robin's body parts
			aScene.getRobin().getHead().addPropertyChangeListener(this);
			aScene.getRobin().getWaist().addPropertyChangeListener(this);
			aScene.getRobin().getArms().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getRobin().getArms().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getRobin().getLegs().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getRobin().getLegs().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getRobin().getText().addPropertyChangeListener(this);
			// Register with Lancelot's body parts
			aScene.getLancelot().getHead().addPropertyChangeListener(this);
			aScene.getLancelot().getWaist().addPropertyChangeListener(this);
			aScene.getLancelot().getArms().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getLancelot().getArms().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getLancelot().getLegs().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getLancelot().getLegs().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getLancelot().getText().addPropertyChangeListener(this);
			// Register with Galahad's body parts
			aScene.getGalahad().getHead().addPropertyChangeListener(this);
			aScene.getGalahad().getWaist().addPropertyChangeListener(this);
			aScene.getGalahad().getArms().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getGalahad().getArms().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getGalahad().getLegs().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getGalahad().getLegs().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getGalahad().getText().addPropertyChangeListener(this);
			// Register with Guard's body parts
			aScene.getGuard().getHead().addPropertyChangeListener(this);
			aScene.getGuard().getWaist().addPropertyChangeListener(this);
			aScene.getGuard().getArms().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getGuard().getArms().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getGuard().getLegs().getLeftLine()
					.addPropertyChangeListener(this);
			aScene.getGuard().getLegs().getRightLine()
					.addPropertyChangeListener(this);
			aScene.getGuard().getText().addPropertyChangeListener(this);
			// Register with other atomic shapes
			aScene.getGuardArea().addPropertyChangeListener(this);
			aScene.getKnightArea().addPropertyChangeListener(this);
			aScene.getGorge().getFirstLine().addPropertyChangeListener(this);
			aScene.getGorge().getSecondLine().addPropertyChangeListener(this);
			aScene.getBridge().getFirstLine().addPropertyChangeListener(this);
			aScene.getGorge().getSecondLine().addPropertyChangeListener(this);
		}

		public void propertyChange(PropertyChangeEvent event) {
			repaint();
		}

		public void paint(Graphics g) {
			//System.out.println("come into paint");
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5.0f));
			draw(g2, aScene);
		}

		public void draw(Graphics2D g, BridgeSceneInterface scene) {
			// Draws non-body part atomic shapes 
			draw(g, scene.getGuardArea());
			draw(g, scene.getKnightArea());
			draw(g, scene.getGorge().getFirstLine());
			draw(g, scene.getGorge().getSecondLine());
			draw(g, scene.getBridge().getFirstLine());
			draw(g,scene.getBridge().getSecondLine());
			//Draws Arthur
			draw(g, scene.getArthur().getHead());
			draw(g, scene.getArthur().getWaist());
			draw(g, scene.getArthur().getArms().getLeftLine());
			draw(g, scene.getArthur().getArms().getRightLine());
			draw(g, scene.getArthur().getLegs().getLeftLine());
			draw(g, scene.getArthur().getLegs().getRightLine());
			draw(g, scene.getArthur().getText());
			// Draws Robin
			draw(g, scene.getRobin().getHead());
			draw(g, scene.getRobin().getWaist());
			draw(g, scene.getRobin().getArms().getLeftLine());
			draw(g, scene.getRobin().getArms().getRightLine());
			draw(g, scene.getRobin().getLegs().getLeftLine());
			draw(g, scene.getRobin().getLegs().getRightLine());
			draw(g, scene.getRobin().getText());
			// Draws Lancelot
			draw(g, scene.getLancelot().getHead());
			draw(g, scene.getLancelot().getWaist());
			draw(g, scene.getLancelot().getArms().getLeftLine());
			draw(g, scene.getLancelot().getArms().getRightLine());
			draw(g, scene.getLancelot().getLegs().getLeftLine());
			draw(g, scene.getLancelot().getLegs().getRightLine());
			draw(g, scene.getLancelot().getText());
			// Draws Galahad
			draw(g, scene.getGalahad().getHead());
			draw(g, scene.getGalahad().getWaist());
			draw(g, scene.getGalahad().getArms().getLeftLine());
			draw(g, scene.getGalahad().getArms().getRightLine());
			draw(g, scene.getGalahad().getLegs().getLeftLine());
			draw(g, scene.getGalahad().getLegs().getRightLine());
			draw(g, scene.getGalahad().getText());
			// Draws Guard
			draw(g, scene.getGuard().getHead());
			draw(g, scene.getGuard().getWaist());
			draw(g, scene.getGuard().getArms().getLeftLine());
			draw(g, scene.getGuard().getArms().getRightLine());
			draw(g, scene.getGuard().getLegs().getLeftLine());
			draw(g, scene.getGuard().getLegs().getRightLine());
			draw(g, scene.getGuard().getText());
			setBackground(new Color(34, 139, 34));
			setForeground(new Color(34, 139, 34));
		}

		//Draws Images
		public void draw(Graphics2D g, ImageShape anImage) {
			Image img = Toolkit.getDefaultToolkit().getImage(
					anImage.getImageFileName());
			g.drawImage(img, anImage.getX(), anImage.getY(), this);
		}
		
		//Draws Lines
		public void draw(Graphics2D g, Line aLine) {
			Line2D line = new Line2D.Double();
			line.setLine(aLine.getX(), aLine.getY(),
					aLine.getX() + aLine.getWidth(),
					aLine.getY() + aLine.getHeight());
			
			g.draw(line);
		}
		
		//Draws ovals
		public void draw(Graphics2D g, StandArea anOval) {
			
//			if (anOval.getFilled() == true) {
//				g.fillOval(anOval.getX(), anOval.getY(), anOval.getWidth(),
//						anOval.getHeight());
//			} else
				g.drawOval(anOval.getX(), anOval.getY(), anOval.getWidth(),
						anOval.getHeight());
		}
		//Draws rectangles
		public void draw(Graphics2D g, BoundedShape aRectangle) {
			Rectangle2D rectangle = new Rectangle2D.Double();
			rectangle.setRect(aRectangle.getX(), aRectangle.getY(),
					aRectangle.getWidth(), aRectangle.getHeight());
			
//			if (aRectangle.getFilled() == true) {
//				g.fill(rectangle);
//			}
			g.draw(rectangle);
		}
		//Draws strings
		public void draw(Graphics2D g, StringShape aStringShape) {
			String s = aStringShape.getText();
			g.drawString(s, aStringShape.getX(), aStringShape.getY());
		}
	}

