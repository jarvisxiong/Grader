package grail.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import grail.avatar.Avatar;
import grail.avatar.BasicAvatar;
import grail.paint.ObservablePainter;
import grail.draw.interfaces.ImageShape;
import grail.draw.interfaces.RotatingShape;
import grail.draw.interfaces.StringShape;
import util.annotations.Tags;

@Tags({"ObservingBridgeScenePainter"})
public class AnAvatarView implements AvatarView{
	Avatar currentAvatar;
	ObservablePainter aModel;
	
	public AnAvatarView(Avatar avatar, ObservablePainter model) {
		currentAvatar = avatar;
		this.aModel = model;
		BasicAvatar.addBodyListener(currentAvatar, this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		aModel.repaint();
	}

    public void paint(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(this.currentAvatar.getBodyColor());  
        draw(g2, this.currentAvatar);
    }
    
    public void draw(Graphics2D g, Avatar avatar) {
    	draw(g, avatar.getBody());
    	draw(g, avatar.getHead());
    	draw(g, avatar.getText());
    	draw(g, avatar.getLegs().getLeftLine());
    	draw(g, avatar.getLegs().getRightLine());
    	draw(g, avatar.getArms().getRightLine());
    	draw(g, avatar.getArms().getLeftLine());
        
        
    }

    public void draw(Graphics2D g, StringShape aLabel) {
        String s = aLabel.getText();
        g.drawString(s, aLabel.getX(), aLabel.getY()); 
    }
    
    public  void draw(Graphics2D g, ImageShape anImage) {
        Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
        g.drawImage(img, anImage.getX(), anImage.getY(), ((Component)this.aModel));     
    }
    
    public void draw(Graphics2D g, RotatingShape line) {
    	g.drawLine(line.getX(), line.getY(), line.getX() + line.getWidth(), line.getY() + line.getHeight());
    }


}
