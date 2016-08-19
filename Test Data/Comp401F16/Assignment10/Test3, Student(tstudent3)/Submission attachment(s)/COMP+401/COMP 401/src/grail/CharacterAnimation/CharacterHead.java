package grail.CharacterAnimation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LABEL_PATTERN)
public class CharacterHead extends LocatableShape implements HeadInterface {

	 String imageFileName;
     int x, y, height, width;
     PropertyChangeListener[] contents = new PropertyChangeListener[MAX_SIZE];
     
     public CharacterHead (String initImageFileName, int initX, int initY, int initHeight, int initWidth) {	
    	imageFileName = initImageFileName;
    	x = initX;
    	y = initY;    
    	height = initHeight;
    	width = initWidth;
     }          

    public int getX() {
    	return x;
    }
    
  	public void setX(int newX) {
  		int oldX = x;
  		x = newX;
  		notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, newX));
  	}
  	
  	public int getY() {
  		return y;
  	}
  	
  	public void setXY(int newX, int newY){
  		int oldX = x;
  		x = newX;
  		notifyAllListeners(new PropertyChangeEvent(this, "X", oldX, newX));
  		int oldY = y;
  		y = newY;
  		notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, newY));
  		}
  	
  	public void setY(int newY) {
  		int oldY = y;
  		y = newY;
  		notifyAllListeners(new PropertyChangeEvent(this, "Y", oldY, newY));
  		
  	}
  	
    public int getHeight() { return height; }
  	public void setHeight(int newHeight) {
  		height = newHeight;
  	}
  	
  	public int getWidth() { return width; }
  	public void setWidth(int newWidth) {
  		width = newWidth;
  	}
  	
    public String getImageFileName() {return imageFileName;}  
    public void setImageFileName(String newVal) {imageFileName = newVal ;}     
	
}
