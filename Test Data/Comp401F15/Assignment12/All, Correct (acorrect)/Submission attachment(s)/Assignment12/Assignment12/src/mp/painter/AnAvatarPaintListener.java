package mp.painter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mp.shapes.Avatar;
import mp.shapes.LineShape;

public class AnAvatarPaintListener extends APaintListener implements BackgroundPaintListener{
	protected Avatar avatar;
	
	public AnAvatarPaintListener(Avatar avatar){
		this.avatar = avatar;
		avatar.getArms().getLeftLine().addPropertyChangeListener(this);
		avatar.getArms().getRightLine().addPropertyChangeListener(this);
		avatar.getHead().addPropertyChangeListener(this);
		avatar.getLegs().getLeftLine().addPropertyChangeListener(this);
		avatar.getLegs().getRightLine().addPropertyChangeListener(this);
		avatar.getText().addPropertyChangeListener(this);
		avatar.getTorso().addPropertyChangeListener(this);
	}
	
	public void paint(Graphics2D g){
		BufferedImage head = null;
		try {
			head = ImageIO.read(new File(avatar.getHead().getImageFileName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(head, avatar.getHead().getX(), avatar.getHead().getY(), avatar.getHead().getWidth(), avatar.getHead().getHeight(), null);
		
		g.drawString(avatar.getText().getText(), avatar.getText().getX(), avatar.getText().getY());
		
		LineShape line = avatar.getArms().getLeftLine();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = avatar.getArms().getRightLine();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = avatar.getLegs().getRightLine();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = avatar.getLegs().getLeftLine();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = avatar.getTorso();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
	}
}
