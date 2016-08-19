package grail.scene;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.avatar.Avatar;
import grail.avatar.BasicAvatar;
import grail.draw.interfaces.BridgeMoat;
import grail.draw.interfaces.Platform;
import grail.draw.objects.BasicBridgeMoat;
import grail.draw.objects.PlatformOvalShape;
import grail.factories.SingletonsCreator;
import grail.helpers.BasicTable;
import grail.helpers.Table;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"BridgeScene"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Occupied", "KnightTurn", "KnightList","Arthur", "Lancelot", "Robin", "Galahad","Guard", "knightArea", "GuardArea", "Gorge"})
@EditablePropertyNames({"X", "Y"})
public class BasicBridgeScene implements BridgeScene {
	
	private Avatar arthur, lancelot, robin, galahad, guard;
	private int xSceneLocation, ySceneLocation;
	private Platform knightArea, guardArea;
	private BridgeMoat gorge;
	private boolean occupied, knightTurn;
	private Avatar knightAtSpot;
	private int questionNumber;
	private Table<String,Avatar> knightList;
	private PropertyListenerSupport propertyListenerList;

	
	public BasicBridgeScene() {
		this(new BasicAvatar("arthur.jpg"), new BasicAvatar("lancelot.jpg"), new BasicAvatar("robin.jpg"), new BasicAvatar("galahad.jpg"), new BasicAvatar("guard.jpg"));
	}
	
	public BasicBridgeScene(Avatar arthur, Avatar lancelot, Avatar robin, Avatar galahad, Avatar guard) {
		this.knightList = SingletonsCreator.avatarTableFactoryMethod();
		
		this.propertyListenerList = new APropertyListenerSupport();
		
		this.arthur = arthur;
		arthur.setBodyColor(Color.YELLOW);
		arthur.setTextColor(Color.YELLOW);
		this.knightList.put("arthur", this.arthur);
		
		this.lancelot = lancelot;
		lancelot.setBodyColor(Color.RED);
		lancelot.setTextColor(Color.RED);
		this.knightList.put("lancelot", this.lancelot);
		
		this.galahad = galahad;
		galahad.setBodyColor(Color.PINK);
		galahad.setTextColor(Color.PINK);
		this.knightList.put("galahad", this.galahad);
		
		this.robin = robin;
		robin.setBodyColor(Color.CYAN);
		robin.setTextColor(Color.CYAN);
		this.knightList.put("robin", this.robin);
		
		this.guard = guard;
		guard.setBodyColor(Color.DARK_GRAY);
		guard.setTextColor(Color.DARK_GRAY);
		this.knightList.put("guard", this.guard);
		
		this.xSceneLocation = 0;
		this.ySceneLocation = 0;
		
		this.knightArea = new PlatformOvalShape();
		this.guardArea = new PlatformOvalShape();
		this.gorge = new BasicBridgeMoat();
		
		setInitialScene(this.xSceneLocation, this.ySceneLocation);
		
		guard.setBasicText("I am the Guard!");
		galahad.setBasicText("I am Galahad!");
		robin.setBasicText("I am Robin!");
		lancelot.setBasicText("I am Lancelot!");
		arthur.setBasicText("Hello, I am Arthur!");
		
		
		this.occupied = false;
		this.knightTurn = false;
		
	}
	
	public Avatar getArthur() {
		return arthur;
	}


	public Avatar getLancelot() {
		return lancelot;
	}

	
	public Avatar getRobin() {
		return robin;
	}

	
	public Avatar getGalahad() {
		return galahad;
	}

	
	public Avatar getGuard() {
		return guard;
	}
	
	public Platform getKnightArea() {
		return this.knightArea;
	}
	
	public Platform getGuardArea(){
		return this.guardArea;
	}
	
	private void setInitialScene(int xPos, int yPos) {
		//positions and default locations
		final int firstLinePosXY = 50;
		final int secondLinePosY = 300;
		final int secondLinePosX = 250;
		final int platformPosY = 500;
		final int guardPlotX = 600;
		final int knightPlotX = 350;
		final int bridgeX = 800;
		
		final int firstLineY = firstLinePosXY + yPos;
		final int secondLineY = secondLinePosY + yPos;
		final int lancelotAndGalahadX = secondLinePosX + xPos;
		final int robinAndArthurX = firstLinePosXY + xPos;
		final int platformY = platformPosY + yPos;
		final int platformGuardX = guardPlotX + xPos;
		final int platformKnightX = knightPlotX + xPos;
		final int bridgeStartX = bridgeX + xPos;
		final int moatSizeX = 500;
		final int moatSizeY = 1000;
	
		arthur.setLocation(robinAndArthurX, firstLineY);
	
		lancelot.setLocation(lancelotAndGalahadX, firstLineY);
		
		robin.setLocation(robinAndArthurX, secondLineY);

		galahad.setLocation(lancelotAndGalahadX, secondLineY);

		this.knightArea.setLocation(platformKnightX, platformY);
		this.guardArea.setLocation(platformGuardX, platformY);

		guard.setLocation(this.guardArea.getAvatarX(guard), this.guardArea.getAvatarY(guard));

		
		this.gorge.setLocation(bridgeStartX, 0);
		this.gorge.setSize(moatSizeX, moatSizeY);
		
	}
	
	public void setX(int x) {
		this.xSceneLocation = x;
//		setGraphicScene(this.xSceneLocation, this.ySceneLocation);
	
	}
	
	
	public void setY(int y){
		this.ySceneLocation = y;
//		setGraphicScene(this.xSceneLocation, this.ySceneLocation);
	}
	
	public int getX(){return this.xSceneLocation;}
	
	public int getY(){return this.ySceneLocation;}
	
	@Tags({"scroll"})
	public void scroll(int x, int y) {
		this.arthur.move(x, y);
		this.galahad.move(x, y);
		this.robin.move(x, y);
		this.lancelot.move(x, y);
		this.guard.move(x, y);
		this.guardArea.move(x, y);
		this.knightArea.move(x, y);
		this.gorge.move(x, y);
		this.xSceneLocation = this.xSceneLocation + x;
		this.ySceneLocation = this.ySceneLocation + y;
		
	}

	public BridgeMoat getGorge() {
		return this.gorge;
	}

	public boolean preApproach() {
		return !this.occupied;
	}
	
	@Tags({"approach"})
	public void approach(Avatar avatarToApproach) {
		assert preApproach(): "The knight area is already occupied";
		if (!this.occupied) {
			avatarToApproach.setLocation(knightArea.getAvatarX(avatarToApproach), knightArea.getAvatarY(avatarToApproach));
			this.occupied = true;
			this.questionNumber = 0;
			this.knightAtSpot = avatarToApproach;
			this.knightTurn = false;
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
		} else {
			//do nothing
		}
		
	}

	
	public boolean getOccupied() {
		return this.occupied;
	}

	
	public boolean preSay() {
	return (this.occupied);	
	}
	
	
	@Tags({"say"})
	public void say(String lineOfDialogue) {
		final int maxQuestionsAllowed = 100;
		assert preSay():"The Knight space is not occupied, Can't Say!";
		if (this.occupied && !this.knightTurn) {
			this.guard.setBasicText(lineOfDialogue);
			this.knightTurn = true;
			
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
			
		} else if (this.occupied && this.knightTurn && this.questionNumber < maxQuestionsAllowed){
			this.knightAtSpot.setBasicText(lineOfDialogue);
			this.questionNumber++;
			this.knightTurn = false;
			
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
		} else if (this.occupied && this.knightTurn) {
			this.knightTurn = false;
			failed();
		} else {
			//do nothing
		}
		
	}

	public boolean getKnightTurn() {
		return this.knightTurn;
	}

	public boolean prePassed() {
		return (this.occupied && !this.knightTurn);
	}
	
	@Tags({"passed"})
	public void passed() {
		assert prePassed():"It is not the guards turn.";
		if (this.occupied && !this.knightTurn) {
			marchAvatar();
			this.occupied = false;
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
			
		} else {
			//do nothing
		}
		
	}

	public boolean preFailed() {
		return this.occupied;
	}
	
	@Tags({"failed"})
	public void failed() {
		assert preFailed():"The Knight space is not occupied!";
		if (this.occupied && !this.knightTurn) {
			this.knightAtSpot.setLocation(this.gorge.getFallZoneX(), this.gorge.getFallZoneY());
			fallAvatar();
			this.occupied = false;
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
		} else if (this.occupied && knightTurn) {
			this.guard.setLocation(this.gorge.getFallZoneX(), this.gorge.getFallZoneY());
			fallAvatar();
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
			this.propertyListenerList.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
		} else {
			//do nothing
		}
		
	}

	private void marchAvatar() {
		final int distanceToMarch = 1000;
		final int moveIncrementX = 20;
		final int moveIncrementY = 0;
		final int rotationAmount = 8;
		final int sleepBetweenTimeMS = 200;
		int distanceMarched = 0;
		int position = 0;
		this.occupied = false;
		while (distanceMarched < distanceToMarch) {
			if (position == 0) {
				this.knightAtSpot.move(moveIncrementX, moveIncrementY);
				this.knightAtSpot.getLegs().rotate(-rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 1;
			} else {
				this.knightAtSpot.move(moveIncrementX, moveIncrementY);
				this.knightAtSpot.getLegs().rotate(rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				ThreadSupport.sleep(sleepBetweenTimeMS);
				position = 0;
			}
		}
		
	}
	
	private void fallAvatar() {
		final double shrinkFactor = .95;
		final int shrinkTimes = 20;
		final int waveRotate = 4;
		final int sleepTimeMS = 200;
		if (this.knightTurn) {
			int count = 0;
			while (count < shrinkTimes) {
				this.guard.scale(shrinkFactor);
				this.guard.getArms().rotate(waveRotate);
				this.guard.getLegs().rotate(waveRotate);
				count++;
				ThreadSupport.sleep(sleepTimeMS);
			}
		} else {
			int count = 0;
			while (count < shrinkTimes) {
				this.knightAtSpot.scale(shrinkFactor);
				this.knightAtSpot.getArms().rotate(waveRotate);
				this.knightAtSpot.getLegs().rotate(waveRotate);
				count++;
				ThreadSupport.sleep(sleepTimeMS);
			}
		} 
		
	}

	
	public Table<String,Avatar> getKnightList() {
		return this.knightList;
	}

	public void setOriginalPositions() {
		setInitialScene(this.xSceneLocation,this.ySceneLocation);
		
	}
	
	
	public static void registerListenerToAllSceneObjects(BridgeScene scene, PropertyChangeListener listener) {
		BasicAvatar.addBodyListener(scene.getArthur(), listener);
		BasicAvatar.addBodyListener(scene.getGalahad(), listener);
		BasicAvatar.addBodyListener(scene.getLancelot(), listener);
		BasicAvatar.addBodyListener(scene.getRobin(), listener);
		BasicAvatar.addBodyListener(scene.getGuard(), listener);
		scene.getKnightArea().addPropertyChangeListener(listener);
		PlatformOvalShape.addListenersToSubshapes(scene.getKnightArea(), listener);
		scene.getGuardArea().addPropertyChangeListener(listener);
		PlatformOvalShape.addListenersToSubshapes(scene.getGuardArea(), listener);
		scene.getGorge().addPropertyChangeListener(listener);
		BasicBridgeMoat.addListenerToSubShapes(scene.getGorge(), listener);
	}

	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyListenerList.addElement(arg0);
		
	}
}
