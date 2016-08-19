package grail.CharacterAnimation;

import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@ObserverRegisterer(value = "1")
@Tags({"BridgeScene"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class BridgeScene implements BridgeSceneInterface {
	String name;
	PointInterface place;
	AvatarInterface arthur;
	AvatarInterface galahad;
	AvatarInterface lancelot;
	AvatarInterface robin;
	AvatarInterface guard;
	Boolean occupied = false;
	Boolean knightTurn = false;
	GorgeAndBridgeInterface sceneDetails = new GorgeAndBridge();
	AvatarInterface currentKnight;
	
	final int mod100 = 100;
	final int mod200 = 200;
	final int mod250 = 250;
	final int mod350 = 350;
	final int mod480 = 480;
	final int mod700 = 700;
	final int mod50 = 50;
	final int mod80 = 80;
	final int mod5 = 5;
	final int mod1300 = 1300;
	final int mod500 = 500;
		
	public BridgeScene(){
	name = "arthur.jpg";
	place = new Point(mod100, mod100);
	arthur = new Avatar(name, place);
	
	name = "galahad.jpg";
	place = new Point(mod200, mod100);
	galahad = new Avatar(name, place);
	
	name = "lancelot.jpg";
	place = new Point(mod100,mod350);
	lancelot = new Avatar(name, place);
	
	name = "robin.jpg";
	place = new Point(mod200, mod350);
	robin = new Avatar(name, place);
	
	name = "guard.jpg";
	place = new Point(mod480, mod700);
	guard = new Avatar(name, place);
	
	arthur.getArms().getLeftLine().addPropertyChangeListener(this);
	arthur.getArms().getRightLine().addPropertyChangeListener(this);
	arthur.getLegs().getLeftLine().addPropertyChangeListener(this);
	arthur.getLegs().getRightLine().addPropertyChangeListener(this);
	arthur.getHead().addPropertyChangeListener(this);
	arthur.getBody().addPropertyChangeListener(this);
	arthur.getSpeech().addPropertyChangeListener(this);
	
	galahad.getArms().getLeftLine().addPropertyChangeListener(this);
	galahad.getArms().getRightLine().addPropertyChangeListener(this);
	galahad.getLegs().getLeftLine().addPropertyChangeListener(this);
	galahad.getLegs().getRightLine().addPropertyChangeListener(this);
	galahad.getHead().addPropertyChangeListener(this);
	galahad.getBody().addPropertyChangeListener(this);
	galahad.getSpeech().addPropertyChangeListener(this);
	
	lancelot.getArms().getLeftLine().addPropertyChangeListener(this);
	lancelot.getArms().getRightLine().addPropertyChangeListener(this);
	lancelot.getLegs().getLeftLine().addPropertyChangeListener(this);
	lancelot.getLegs().getRightLine().addPropertyChangeListener(this);
	lancelot.getHead().addPropertyChangeListener(this);
	lancelot.getBody().addPropertyChangeListener(this);
	lancelot.getSpeech().addPropertyChangeListener(this);
	
	robin.getArms().getLeftLine().addPropertyChangeListener(this);
	robin.getArms().getRightLine().addPropertyChangeListener(this);
	robin.getLegs().getLeftLine().addPropertyChangeListener(this);
	robin.getLegs().getRightLine().addPropertyChangeListener(this);
	robin.getHead().addPropertyChangeListener(this);
	robin.getBody().addPropertyChangeListener(this);
	robin.getSpeech().addPropertyChangeListener(this);
	
	guard.getArms().getLeftLine().addPropertyChangeListener(this);
	guard.getArms().getRightLine().addPropertyChangeListener(this);
	guard.getLegs().getLeftLine().addPropertyChangeListener(this);
	guard.getLegs().getRightLine().addPropertyChangeListener(this);
	guard.getHead().addPropertyChangeListener(this);
	guard.getBody().addPropertyChangeListener(this);
	guard.getSpeech().addPropertyChangeListener(this);
	
	
	}
	
	public AvatarInterface getArthur(){
		return arthur;
	}
	
	public AvatarInterface getGalahad(){
		return galahad;
	}
	
	public AvatarInterface getLancelot(){
		return lancelot;
	}
	
	public AvatarInterface getRobin(){
		return robin;
	}
	
	public AvatarInterface getGuard(){
		return guard;
	}
	
	public void rotateAvatarArms(int wave){
		arthur.getArms().getLeftLine().rotate(wave);
	}
	
	/*public void move(){
		robin.move(5, 5);
	}*/
	@Tags({"Aproach"})
	public void moveToCircle(AvatarInterface next){
		int newX = 0;
		int newY = 0;
		currentKnight = next;
		
		if (next.getBody().getLocation().getX() != sceneDetails.getKnightArea().getLocation().getX() + mod50){
			if (next.getBody().getLocation().getX() > sceneDetails.getKnightArea().getLocation().getX() + mod50){
				newX = sceneDetails.getKnightArea().getLocation().getX() - next.getBody().getLocation().getX();
			}
			else {
				newX = mod5;
			}
		}		
			
		if (next.getBody().getLocation().getY() != sceneDetails.getKnightArea().getLocation().getY() - mod80){
			if (next.getBody().getLocation().getY() > sceneDetails.getKnightArea().getLocation().getY() - mod80){
				newY = (int) (sceneDetails.getKnightArea().getLocation().getY() - next.getBody().getLocation().getY() - mod80);
			}
			else{
				newY = mod5;
			}
		}		
			
		next.move(newX, newY);
		if (next.getBody().getLocation().getX() == (sceneDetails.getKnightArea().getLocation().getX() + mod50) &&
				next.getBody().getLocation().getY() == (sceneDetails.getKnightArea().getLocation().getY() - mod80)) {
			occupied = true;
		}
	}
	
	public void scaleAvatar(int scale ){
		lancelot.scale(scale);		
	}
	
	public GorgeAndBridgeInterface getSceneDetails(){
		return sceneDetails;
	}
	
	public void setOccupied(){
		if (occupied){
			occupied = false;
		}
		else {
			occupied = true;
		}
	}
	public boolean getOccupied(){
		return occupied;
	}
	
	public Boolean getKnightTurn(){
		return knightTurn;
	}
	
	
	@Tags({"Say"})
	public void sayScene(String speech){

		if (occupied){
			if (!knightTurn){
				knightTurn = true;
				guard.setSpeech(speech);
			}
			else {
				currentKnight.setSpeech(speech);
				knightTurn = false;
			}
		}

		
	}

	@Tags({"Passed"})
	public boolean passed(){
		if (occupied){
			currentKnight.move(mod5,0);
		}
		if (currentKnight.getBody().getLocation().getX() > mod1300){
			occupied = false;
			return true;
		}
		else {
			return false;
		}
	}	
	
	@Tags({"Failed"})
	public  void failed(){
		if (occupied ){
			if (!knightTurn ){
				currentKnight.move(mod500, mod350);
				occupied = false;
			}
		}
	}
	
	@Tags({"Scroll"})
	public void scroll(int xScroll, int yScroll){
		arthur.move(xScroll, yScroll);
		galahad.move(xScroll, yScroll);
		lancelot.move(xScroll, yScroll);
		robin.move(xScroll, yScroll);
		guard.move(xScroll, yScroll);
		sceneDetails.moveAll(xScroll, yScroll);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		

}