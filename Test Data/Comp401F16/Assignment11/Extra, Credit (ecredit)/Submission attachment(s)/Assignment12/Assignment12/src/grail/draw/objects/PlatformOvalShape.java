package grail.draw.objects;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.avatar.Avatar;
import grail.draw.interfaces.OvalShape;
import grail.draw.interfaces.Platform;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "Color", "Filled", "Platform", "Shadow"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "Color", "Filled"})
public class PlatformOvalShape extends BasicOvalShape implements Platform{
	private OvalShape platform, shadow;
	
	public PlatformOvalShape() {
		this(0,0);
		
	}
	
	public PlatformOvalShape(int x, int y) {
		final int defaultWidth = 120;
		final int defaultHeight = 25;
		this.x = x;
		this.y = y;
		this.color = Color.BLACK;
		this.platform = new BasicOvalShape();
		this.shadow = new BasicOvalShape();
		this.platform.setColor(Color.DARK_GRAY);
		this.shadow.setColor(Color.lightGray);
		this.width = defaultWidth;
		this.height = defaultHeight;
		buildPlatform(this.width, this.height);
	}

	
	public void setHeight(int newVal) {
		int oldHeight = this.height;
		this.height = newVal;
		buildPlatform(this.width,this.height);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldHeight, this.height));
		}
	}

	
	public void setWidth(int newVal) {
		int oldWidth = this.width;
		this.width = newVal;
		buildPlatform(this.width,this.height);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldWidth, this.width));
		}
	}


	
	public void setX(int x) {
		int oldX = this.x;
		this.x = x;
		setLocation(this.x,this.y);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "X", oldX, this.x));
		}
	}
	
	public void setY(int y) {
		int oldY = this.y;
		this.y = y;
		setLocation(this.x,this.y);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Y", oldY, this.y));
		}
	}

	
	private void buildPlatform(int width, int height) {
		final int platformHeight = height; //25
		final int platformWidth = width; //100
		final double scaleFactor = .2;
		final double halfValue = 2;
		final int shadowHeight = (int)((height * scaleFactor) + height);
		final int shadowWidth =(int) ((width * scaleFactor) +  width);
		this.platform.setX(x);
		this.platform.setY(y);
		this.platform.setHeight(platformHeight);
		this.platform.setWidth(platformWidth);
		
		final int shadowOffX = (int)((width* scaleFactor)/halfValue);
		final int shadowOffY = (int)((height * scaleFactor)/halfValue);
		this.shadow.setX(x - shadowOffX);
		this.shadow.setY(y - shadowOffY);
		this.shadow.setHeight(shadowHeight);
		this.shadow.setWidth(shadowWidth);
		
	}
	
	public void move(int x, int y) {
		this.platform.setX(this.platform.getX() + x);
		this.platform.setY(this.platform.getY() + y);
		this.shadow.setX(this.shadow.getX() + x);
		this.shadow.setY(this.shadow.getY()  + y);
		this.x = this.x + x;
		this.y = this.y + y;
	}

	
	public void setLocation(int x, int y) {
		int xChange = x - this.x;
		int yChange = y - this.y;
		move(xChange, yChange);	
	}

	
	public OvalShape getShadow() {
		return this.shadow;
	}
	
	
	public OvalShape getPlatform() {
		return this.platform;
	}

	
	public int getAvatarX(Avatar aAvatar) {
		return this.x + Math.abs(this.width/2) - Math.abs(aAvatar.getHead().getWidth()/2);
	}

	
	public int getAvatarY(Avatar aAvatar) {
		return this.y - aAvatar.getHeight();
	}
	
	public static void addListenersToSubshapes(Platform shape, PropertyChangeListener listener) {
		shape.getPlatform().addPropertyChangeListener(listener);
		shape.getShadow().addPropertyChangeListener(listener);
	}

}
