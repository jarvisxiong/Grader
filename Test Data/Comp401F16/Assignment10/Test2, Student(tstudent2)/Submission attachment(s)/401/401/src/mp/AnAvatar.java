package mp;

import mp.Locatable;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"Avatar"})
public class AnAvatar extends Locatable implements Avatar{
	String Hello = "";
	StringShape Text;
	Image Head;
	VShape Arms;
	Line Body;
	VShape Legs;
	
	
	// Body Parts------------------------------------------------------------------------------------------------
	public AnAvatar(String fileName, int originX, int originY){
		x = originX;
		y = originY;
		Text = new AStringShape(Hello, originX + 25, originY - 75);
		Head = new AnImage(fileName, x - 20, y - 63);
		Arms = new AVShape(originX, originY);
		Body = new ALine(originX, originY, originY + 50, originX);
		Legs = new AVShape(originX, originY + 50);
		
	}
	// Move Methods -----------------------------------------------------------------------------------------------
	
	public void move(int stepsX, int stepsY){
		
		int moveX = stepsX*5;
		int moveY = stepsY*5;
		
		x += moveX;
		y += moveY;
		
		// Move Text
		Text.setX(x + 25);
		Text.setY(y - 75);
		
		// Move Head
		Head.setX(x - 20);
		Head.setY(y - 63);
		
		// Move Arms
		Arms.getLeftLine().setX(x);
		Arms.getRightLine().setX(x);
		Arms.getLeftLine().setY(y);
		Arms.getRightLine().setY(y);
	
		// Move Body
		Body.setX(x);
		Body.setY(y);
		
		// Move Legs
		Legs.getLeftLine().setX(x);
		Legs.getRightLine().setX(x);
		Legs.getLeftLine().setY(y+50);
		Legs.getRightLine().setY(y+50);
		
	}
	
	public void animatedMove(int moveX, int moveY){
		if(moveX > 0){
			for(int i = 0; i < moveX; i++){
				preciseMove(1, 0);
			}
		}
		if(moveX < 0){
			for(int i = 0; moveX < i; i--){
				preciseMove(-1, 0);
			}
		}
		if(moveY > 0){
			for(int i = 0; i < moveY; i++){
				preciseMove(0, 1);
			}
		}
		if(moveY < 0){
			for(int i = 0; moveY < i; i--){
				preciseMove(0, -1);
			}
		}
	}
	
	
	public void preciseMove(int newX, int newY){
		x += newX;
		y += newY;
		
		// Move Text
		Text.setX(x + 25);
		Text.setY(y - 75);
		
		// Move Head
		Head.setX(x - 20);
		Head.setY(y - 63);
		
		// Move Arms
		Arms.getLeftLine().setX(x);
		Arms.getRightLine().setX(x);
		Arms.getLeftLine().setY(y);
		Arms.getRightLine().setY(y);
	
		// Move Body
		Body.setX(x);
		Body.setY(y);
		
		// Move Legs
		Legs.getLeftLine().setX(x);
		Legs.getRightLine().setX(x);
		Legs.getLeftLine().setY(y+50);
		Legs.getRightLine().setY(y+50);
	}

	// Relocate Method -----------------------------------------------------------------------------------------------
	
	public void relocate(int newX, int newY){
		int moveX = newX*5;
		int moveY = newY*5;
		x += moveX;
		y += moveY;
		
		// Move Text
		Text.setX(x + 25);
		Text.setY(y - 75);
				
		// Move Head
		Head.setX(x - 20);
		Head.setY(y - 63);
				
		// Move Arms
		Arms.getLeftLine().setX(x);
		Arms.getRightLine().setX(x);
		Arms.getLeftLine().setY(y);
		Arms.getRightLine().setY(y);
			
		// Move Body
		Body.setX(x);
		Body.setY(y);
				
		// Move Legs
		Legs.getLeftLine().setX(x);
		Legs.getRightLine().setX(x);
		Legs.getLeftLine().setY(y+50);
		Legs.getRightLine().setY(y+50);
	}
	
	// Getters -----------------------------------------------------------------------------------------------
	
	public StringShape getText(){ return Text;}
	
	public Image getHead(){return Head;}
	
	public VShape getArms(){return Arms;}
	
	public Line getBody(){return Body;}
	
	public VShape getLegs(){return Legs;}
}
