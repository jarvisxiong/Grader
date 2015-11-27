package grail.shape;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"BridgeScene"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

@PropertyNames({"arthur","lancelot","robin","galahad","guard","bridge","gorge","knightArea","guardArea","occupied","knightTurn"})
//@EditablePropertyNames({"x","y"})
public class BridgeScene implements BridgeSceneInterface{
//Arthur, Lancelot, Robin, Galahad, and Guard
	AvatarInterface arthur;
	AvatarInterface lancelot;
	AvatarInterface robin;
	AvatarInterface galahad;
	AvatarInterface guard;
	ParallelLinesInterface bridge;
	ParallelLinesInterface gorge;
	Shape knightArea,guardArea;
	private boolean occupied;//for knight
	private boolean guardAreaOccupied;//for guard
	AvatarInterface standKnight;
	boolean knightTurn;
	
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	public BridgeScene()
	{
		knightTurn=false;
		occupied=false;
		guardAreaOccupied=true;
		standKnight=null;
		String path="images/";
		arthur=new Avatar("Arthur",path+"arthur.jpg",200,300);
		lancelot=new Avatar("Lancelot",path+"lancelot.jpg",100,300);
		robin=new Avatar("Robin",path+"robin.jpg",200,100);
		galahad=new Avatar("Galahad",path+"galahad.jpg",100,100);
		guard=new Avatar("Guard",path+"guard.jpg",520,250);
		gorge=new Gorge(800,20,600,20);
		bridge=new Bridge(600,300,600,350);
		knightArea=new StandArea(300,300);
		guardArea=new StandArea(500,300);
		
		
		//add addPropertyChangeListener for all the instance variables
		arthur.getHead().addPropertyChangeListener(this);
		arthur.getArms().getLeftLine().addPropertyChangeListener(this);
		arthur.getArms().getRightLine().addPropertyChangeListener(this);
		arthur.getWaist().addPropertyChangeListener(this);
		arthur.getLegs().getLeftLine().addPropertyChangeListener(this);
		arthur.getLegs().getRightLine().addPropertyChangeListener(this);
		arthur.getText().addPropertyChangeListener(this);
		
		lancelot.getHead().addPropertyChangeListener(this);
		lancelot.getArms().getLeftLine().addPropertyChangeListener(this);
		lancelot.getArms().getRightLine().addPropertyChangeListener(this);
		lancelot.getWaist().addPropertyChangeListener(this);
		lancelot.getLegs().getLeftLine().addPropertyChangeListener(this);
		lancelot.getLegs().getRightLine().addPropertyChangeListener(this);
		lancelot.getText().addPropertyChangeListener(this);
		
		robin.getHead().addPropertyChangeListener(this);
		robin.getArms().getLeftLine().addPropertyChangeListener(this);
		robin.getArms().getRightLine().addPropertyChangeListener(this);
		robin.getWaist().addPropertyChangeListener(this);
		robin.getLegs().getLeftLine().addPropertyChangeListener(this);
		robin.getLegs().getRightLine().addPropertyChangeListener(this);
		robin.getText().addPropertyChangeListener(this);
		
		galahad.getHead().addPropertyChangeListener(this);
		galahad.getArms().getLeftLine().addPropertyChangeListener(this);
		galahad.getArms().getRightLine().addPropertyChangeListener(this);
		galahad.getWaist().addPropertyChangeListener(this);
		galahad.getLegs().getLeftLine().addPropertyChangeListener(this);
		galahad.getLegs().getRightLine().addPropertyChangeListener(this);
		galahad.getText().addPropertyChangeListener(this);
		
		guard.getHead().addPropertyChangeListener(this);
		guard.getArms().getLeftLine().addPropertyChangeListener(this);
		guard.getArms().getRightLine().addPropertyChangeListener(this);
		guard.getWaist().addPropertyChangeListener(this);
		guard.getLegs().getLeftLine().addPropertyChangeListener(this);
		guard.getLegs().getRightLine().addPropertyChangeListener(this);
		guard.getText().addPropertyChangeListener(this);
		
		gorge.getFirstLine().addPropertyChangeListener(this);
		gorge.getSecondLine().addPropertyChangeListener(this);
		
		bridge.getFirstLine().addPropertyChangeListener(this);
		bridge.getSecondLine().addPropertyChangeListener(this);
		
		knightArea.addPropertyChangeListener(this);
		guardArea.addPropertyChangeListener(this);	
	}
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}
	
	public boolean preApproach()
	{
		return !occupied;
	}
	
	@Tags({"approach"})
	public void approachScene(AvatarInterface person)
	{
		assert preApproach();
		//define destination coordinates
		if (!this.occupied)
		{
			standKnight=person;
			final int x=330,y=250;
			final int dx=x-person.getAvatarLocation().getX();
			final int dy=y-person.getAvatarLocation().getY();
			person.move(dx, dy);
			this.occupied=true;
			
			/**
			 * public PropertyChangeEvent(Object source,
                           String propertyName,
                           Object oldValue,
                           Object newValue)
			 */
			if (propertyListenerSupport != null) {
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "approach", false));
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "passed", true));
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "failed", true));
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "say", true));
			}
		}else
		{
			System.out.println("the knight area is occupied");
		}
	}
	
	public boolean preSay(){
		return occupied && guardAreaOccupied;
	}
	@Tags({"say"})
	public void sayScene(String word)
	{
		assert preSay();
		if (this.occupied&& guardAreaOccupied)
		{
			if (knightTurn)
			{
				this.standKnight.getText().setText(word);
				knightTurn=!knightTurn;

				if (propertyListenerSupport != null) {
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "passed", false));
				}
			}else
			{
				this.guard.getText().setText(word);
				knightTurn=!knightTurn;

				if (propertyListenerSupport != null) {
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "passed", true));
				}
			}
		}
		else
		{
			knightTurn=false;
			System.out.println("the knight area is not occupied");
		}
	}
	
	public boolean prePass(){
		return this.occupied && this.guardAreaOccupied && !this.knightTurn;
	}
	@Tags({"passed"})
	public void passScene()
	{
		assert prePass();
		if (this.occupied&&this.guardAreaOccupied&&!this.knightTurn)
		{
			final int x=800,y=300;
			final int dx=x-standKnight.getAvatarLocation().getX();
			final int dy=y-standKnight.getAvatarLocation().getY();
			standKnight.move(dx, dy);
			this.occupied=false;
			if (propertyListenerSupport != null)
			{
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "approach", true));
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "failed", false));
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "passed", false));
				propertyListenerSupport
						.notifyAllListeners(new PropertyChangeEvent(this,
								"this", "say", false));
			}
			
			this.standKnight=null;
			this.knightTurn=false;
		}
	}
	
	public boolean preFail() {
		return this.occupied && this.guardAreaOccupied;
	}
	
	@Tags({"failed"})
	public void failScene()
	{
		assert preFail();
		if (this.occupied&&this.guardAreaOccupied)
		{
			final int x=600,y=300;
			if (this.knightTurn)//guard fails
			{
				
				//move guard to gorge area
				int dx=x-this.guard.getAvatarLocation().getX();
				int dy=y-this.guard.getAvatarLocation().getY();
				this.guard.move(dx, dy);
				//this.guard=null;
				this.guardAreaOccupied=false;
				if (propertyListenerSupport != null) {
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "failed", false));
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "say", false));
				}
				this.knightTurn=false;
			}
			else//knight fails
			{
				final int dx=x-this.standKnight.getAvatarLocation().getX();
				final int dy=y-this.standKnight.getAvatarLocation().getY();
				this.standKnight.move(dx, dy);
				this.standKnight=null;
				this.occupied=false;
				this.knightTurn=false;
				if (propertyListenerSupport != null) {
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "approach", true));
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "failed", false));
					propertyListenerSupport
							.notifyAllListeners(new PropertyChangeEvent(this,
									"this", "say", false));
				}
			}
		}
	}
	
	//move all objects in this scene
	@Tags({"scroll"})
	public void scrollScene(int dx,int dy)
	{
		this.arthur.move(dx, dy);
		this.bridge.move(dx, dy);
		this.galahad.move(dx, dy);
		this.gorge.move(dx, dy);
		this.guard.move(dx, dy);
		this.guardArea.move(dx, dy);
		this.knightArea.move(dx, dy);
		this.lancelot.move(dx, dy);
		this.robin.move(dx, dy);
	}
	
	public boolean getOccupied()
	{
		return this.occupied;
	}
	public boolean getKnightTurn()
	{
		return this.knightTurn;
	}
	public AvatarInterface getArthur()
	{
		return arthur;
	}
	public AvatarInterface getLancelot()
	{
		return lancelot;
	}
	
	public AvatarInterface getRobin()
	{
		return robin;
	}
	public AvatarInterface getGalahad()
	{
		return galahad;
	}
	public AvatarInterface getGuard()
	{
		return guard;
	}
	public ParallelLinesInterface getGorge()
	{
		return this.gorge;
	}
	public ParallelLinesInterface getBridge()
	{
		return this.bridge;
	}
	@Tags({"KnightArea"})
	public Shape getKnightArea()
	{
		return knightArea;
	}
	@Tags({"GuardArea"})
	public Shape getGuardArea()
	{
		return guardArea;
	}


	public int listenerCount() {
		return propertyListenerSupport.size();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
