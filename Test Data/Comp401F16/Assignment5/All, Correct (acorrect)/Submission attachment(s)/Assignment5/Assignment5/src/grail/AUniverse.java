package grail;
import util.annotations.Visible;
public class AUniverse implements Universe {
	
	Line theLine;
	StringShape theText;
	int x, y;
	Point lineLocation;
	ImageShape image;
	
	public AUniverse (){
		lineLocation = new APoint(30,30);
		theLine = new ALine(lineLocation,80,0);
		theText = new AStringShape("Hello World!", 450,90);
		x = 30;
		y = 120;
		image = new AShapeImage("shuttle.jpg", 300, 30, 100,200);
	}
	
	public Line getLine(){return theLine;}	
	public StringShape getText(){return theText;}
	public ImageShape getImage(){return image;}
	public void setX(int newX){x = newX;}
	public void setY(int newY){y = newY;}
	public int getX(){return x;}	
	public int getY(){return y;}
	// put @Visible(false) before the getter method
	public Point getLineLocation(){return lineLocation;}
	
	public void moveLine(int newX, int newY){
		lineLocation.setX(lineLocation.getX() + newX);
		lineLocation.setY(lineLocation.getY() + newY);
	}
	
	public void moveText(int newX, int newY){
		theText.setX(theText.getX()+newX);
		theText.setY(theText.getY()+newY);
	}
	
	public void moveImage(int newX, int newY){
		image.setX(image.getX()+newX);
		image.setY(image.getY()+newY);
	}
	
	public void moveAll(int newX, int newY){
		moveLine(newX, newY);
		moveText(newX, newY);
		moveImage(newX, newY);
	}
	
	public void move(int newX, int newY){
		lineLocation.setX(lineLocation.getX() + newX);
		lineLocation.setY(lineLocation.getY() + newY);
	}
}
