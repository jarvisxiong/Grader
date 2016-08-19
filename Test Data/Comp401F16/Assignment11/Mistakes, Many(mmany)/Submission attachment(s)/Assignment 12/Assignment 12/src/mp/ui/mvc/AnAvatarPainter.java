package mp.ui.mvc;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;

import mp.ui.AnOval;
import mp.ui.Avatar;
import mp.ui.BridgeScene;
import mp.ui.Head;
import mp.ui.LocatableString;
import mp.ui.RotatingLine;
import mp.ui.Shape;
import mp.ui.VShape;
import util.annotations.Tags;

@Tags("PaintListener")

public class AnAvatarPainter extends Component implements PaintListener {
	
	Avatar avatar;
	ObservableBridgeScenePainter painter;
	
	public AnAvatarPainter(Avatar initAvatar, ObservableBridgeScenePainter initPainter) {
		avatar = initAvatar;
		painter = initPainter;
//		avatar.getArms().getLeftLine().addPropertyChangeListener(this);
//		avatar.getArms().getRightLine().addPropertyChangeListener(this);
//		avatar.getLegs().getLeftLine().addPropertyChangeListener(this);
//		avatar.getLegs().getRightLine().addPropertyChangeListener(this);
//		avatar.getBody().addPropertyChangeListener(this);
//		avatar.getHead().addPropertyChangeListener(this);
//		avatar.getText().addPropertyChangeListener(this);	
		painter.addPaintListener(this);		
	}

	public void paint(Graphics2D g2) {
		draw(g2, avatar);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		paint(g2);
	}
	public static void draw(Graphics g, Avatar avatar)
	{
		draw(g,avatar.getArms());
		draw(g,avatar.getLegs());
		draw(g,avatar.getBody());
		draw(g,avatar.getText());
		draw(g,avatar.getHead());
	}
	public static void draw(Graphics g, VShape angle)
	{
		draw(g,angle.getLeftLine());
		draw(g,angle.getRightLine());
	}
	public static void draw(Graphics g, RotatingLine line)
	{
		g.drawLine(line.getX(),line.getY(),line.getX()+line.getWidth(),line.getY()+line.getHeight());
	}
	public static void draw(Graphics g, LocatableString string)
	{
		String str = string.getText();
		g.drawString(str, string.getX(), string.getY());
	}
	public static void draw(Graphics g, Head anImage)
	{
		Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
		g.drawImage(img, anImage.getX(), anImage.getY(), null);
	}
//	public void propertyChange(PropertyChangeEvent evt) {
//		System.out.println(evt);
//		painter.propertyChange(evt);
//	}


}
