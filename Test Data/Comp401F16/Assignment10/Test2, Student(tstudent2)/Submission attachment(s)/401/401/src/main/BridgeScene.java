package main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.ACircle;
import mp.AGorge;
import mp.AnAvatar;
import mp.Avatar;
import mp.Circle;
import mp.Gorge;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"BridgeScene"})
@PropertyNames({"Arthur", "Galahad","Lancelot","Robin", "Guard","Gorge","knightArea","guardArea", "Occupied", "KnightTurn"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class BridgeScene implements BridgeSceneInterface{
	// Setting up parameters------------------------------------------------------------------------------------------------------------------------------------
		// Images
		private static final String artPic = "arthur.jpg";
		private static final String galPic = "galahad.jpg";
		private static final String lanPic = "lancelot.jpg";
		private static final String robPic = "robin.jpg";
		private static final String guaPic = "guard.jpg";
		
		// Avatar---------------------------------------------------------------------------------------------------------------------------------------------
		Avatar Arthur, Galahad, Lancelot, Robin, Guard;
		
		// Scenario--------------------------------------------------------------------------------------------------------------------------------------------
		Gorge gorge;
		Circle knightArea, guardArea;
		
		// Other Parameters-------------------------------------------------------------------------------------------------------------------------------------
		
		int x;
		int y;
		boolean Occupied; 
		boolean KnightTurn;
		Avatar standingKnight; 
		PropertyListenerSupport propertySupport;
		
		// Constructors--------------------------------------------------------------------------------------------------------------------------------------------
		public BridgeScene(){
			Arthur = new AnAvatar(artPic, 100, 100);
			Galahad = new AnAvatar(galPic, 200, 100);
			Lancelot = new AnAvatar(lanPic, 300, 100);
			Robin = new AnAvatar(robPic, 400, 100);
			gorge = new AGorge();
			
			// Gorge parameters
			int knightareaX = gorge.getBridge().getX() - 175;
			int knightareaY = gorge.getBridge().getY();
			int diameter = 60;
			int guardareaX = gorge.getBridge().getX() - 75;
			int guardareaY = gorge.getBridge().getY(); 
	
			x = knightareaX;
			y = knightareaY;
			knightArea = new ACircle(knightareaX,knightareaY,diameter, diameter);
			guardArea = new ACircle(guardareaX,guardareaY,diameter, diameter);
			Guard = new AnAvatar(guaPic, guardareaX + diameter/2, guardareaY + diameter/2 - 75);
			Occupied = false;
			KnightTurn = false;
			propertySupport = new APropertyListenerSupport();
		}
		
		@Tags({"approach"})
		public void approach(Avatar avatar){
			assert preApproach();
			if(Occupied == false){
				int destinationX = knightArea.getX() + knightArea.getWidth()/2;
				int destinationY = Guard.getArms().getLeftLine().getY();
				int avatarX = avatar.getArms().getLeftLine().getX();
				int avatarY = avatar.getArms().getRightLine().getY();
				int approachX = destinationX - avatarX;
				int approachY = destinationY - avatarY;
				avatar.animatedMove(approachX, approachY);
				standingKnight = avatar;
				Occupied = true;
				KnightTurn = false;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Occupied",false, true));
			}
		}
		
		@Tags({"passed"})
		public void passed(){
			assert prePassed();
			if(Occupied == true){
				int ahead = gorge.getBridge().getX() + gorge.getBridge().getWidth() + gorge.getBridge().getWidth()/2;
				int standing = standingKnight.getArms().getLeftLine().getX();
				int pass = ahead - standing;
				standingKnight.getText().setText("");
				Guard.getText().setText("");
				standingKnight.animatedMove(pass, 0);
				Occupied = false;
				KnightTurn = false;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Occupied",true, false));
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "KnightTurn",true, false));
			}
		}
		
		@Tags({"failed"})
		public void failed(){
			assert preFailed();
			if(Occupied == true && KnightTurn == false){
				int avatarX = standingKnight.getArms().getLeftLine().getX();
				int avatarY = standingKnight.getArms().getLeftLine().getY();
				int banishedX = gorge.getG2().getX() - gorge.getG1().getX();
				int banishedY = gorge.getG1().getHeight()/5;
				int failedX = gorge.getG1().getX() - avatarX + banishedX/2;
				int failedY = banishedY - avatarY;
				standingKnight.getText().setText("");
				Guard.getText().setText("");
				standingKnight.animatedMove(failedX, failedY);
				Occupied = false;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Occupied",true, false));
			}else if(Occupied == true && KnightTurn == true){
				int avatarX = Guard.getArms().getLeftLine().getX();
				int avatarY = Guard.getArms().getLeftLine().getY();
				int banishedX = gorge.getG2().getX() - gorge.getG1().getX();
				int banishedY = gorge.getG1().getHeight()/5;
				int failedX = gorge.getG1().getX() - avatarX + banishedX/2;
				int failedY = banishedY - avatarY;
				standingKnight.getText().setText("");
				Guard.getText().setText("");
				Guard.animatedMove(failedX, failedY);
				Occupied = true;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Occupied",true, false));
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "KnightTurn",false, true));
			}
		}
		
		@Tags({"say"})
		public void say(String text){
			assert preSay();
			if(Occupied == false){}
			else if(Occupied == true && KnightTurn == false){
				standingKnight.getText().setText("");
				Guard.getText().setText(text);
				KnightTurn = true;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "KnightTurn",false, true));
			}else if(Occupied == true && KnightTurn == true){
				Guard.getText().setText("");
				standingKnight.getText().setText(text);
				KnightTurn = false;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "KnightTurn",true, false));
			}
		}
		
		@Tags({"Occupied"})
		public boolean getOccupied(){
			return Occupied;
			
		}
		
		@Tags({"KnightTurn"})
		public boolean getKnightTurn(){
			return KnightTurn;
			
		}
		
		
		
		
		
		// Getter for Avatars----------------------------------------------------------------------------------------------------------------------------------------

		public Avatar getArthur(){return Arthur;}
		public Avatar getGalahad(){return Galahad;}
		public Avatar getLancelot(){return Lancelot;}
		public Avatar getRobin(){return Robin;}
		public Avatar getGuard(){return Guard;}
		public Gorge getGorge(){return gorge;}
		public Circle getKnightArea(){return knightArea;}
		public Circle getGuardArea(){return guardArea;}



		// Adding PropertyChange----------------------------------------------------------------------------------------------------------------------------------------
		@Override
		public void addPropertyChangeListener(InheritingBridgeScenePainter inheritingBridgeScenePainter) {
			// TODO Auto-generated method stub
		}
		
		// Assertion----------------------------------------------------------------------------------------------------------------------------------------
		
		public boolean preApproach(){
			return Occupied == false;
		}
		
		public boolean preSay(){
			return Occupied == true;
		}
		
		public boolean prePassed(){
			return Occupied == true;
		}
		
		public boolean preFailed(){
			return Occupied == true;
		}

		@Override
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			propertySupport.add(listener);				
		}

}
