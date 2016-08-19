package grail.view;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.avatar.Avatar;
import grail.draw.interfaces.ImageShape;
import grail.draw.interfaces.RotatingShape;
import grail.draw.interfaces.StringShape;
import grail.paint.PaintListener;

public interface AvatarView extends PaintListener, PropertyChangeListener {

	public void propertyChange(PropertyChangeEvent evt); 

    public void paint(Graphics2D g); 
    
    public void draw(Graphics2D g, Avatar avatar);

    public void draw(Graphics2D g, StringShape aLabel);
    
    public  void draw(Graphics2D g, ImageShape anImage);
    
    public void draw(Graphics2D g, RotatingShape line);
}
