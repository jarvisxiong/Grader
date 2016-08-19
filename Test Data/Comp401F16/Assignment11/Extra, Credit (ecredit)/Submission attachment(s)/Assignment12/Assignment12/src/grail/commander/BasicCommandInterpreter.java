package grail.commander;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import grail.animation.AnimationCommandObject;
import grail.animation.Animator;
import grail.animation.BasicAvatarAnimator;
import grail.animation.BasicLockstepAnimator;
import grail.animation.LockstepAnimator;
import grail.animation.LockstepCommandObject;
import grail.factories.SingletonsCreator;
import grail.helpers.ClearanceManager;
import grail.helpers.CommandParser;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import grail.scene.BridgeScene;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"CommandInterpreter", "SignedMove", "ErrorResilient"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Command", "Errors"})
@EditablePropertyNames({"Command"})
public class BasicCommandInterpreter implements CommandInterpreter{
	private BridgeScene scene;
	private String currentCommand;
	private String errors;
	private Runnable currentCommandRunnable;
	private String walkCommand = "walk";
	private String clapCommand = "clap";
	private String marchCommand = "march";
	private CommandParser parse;
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	private Animator arthurAnimator, galahadAnimator, lancelotAnimator, robinAnimator, guardAnimator;
	private LockstepAnimator arthurLock, galahadLock, lancelotLock, robinLock, guardLock;
	private ClearanceManager manager;
	private final int componentWidth = 400;
	
	public BasicCommandInterpreter(BridgeScene scene, ClearanceManager manager) {
		this.scene = scene;
		this.currentCommand = "";
		this.errors = "";
		this.manager = manager;	
		this.parse = SingletonsCreator.parserFactoryMethod();
		this.arthurAnimator = new BasicAvatarAnimator();
		this.galahadAnimator = new BasicAvatarAnimator();
		this.lancelotAnimator = new BasicAvatarAnimator();
		this.robinAnimator = new BasicAvatarAnimator();
		this.guardAnimator = new BasicAvatarAnimator();
		this.arthurLock = new BasicLockstepAnimator(manager);
		this.galahadLock = new BasicLockstepAnimator(manager);
		this.lancelotLock = new BasicLockstepAnimator(manager);
		this.robinLock = new BasicLockstepAnimator(manager);
		this.guardLock = new BasicLockstepAnimator(manager);
		}
	
	public BasicCommandInterpreter(BridgeScene scene) {
		this(scene, SingletonsCreator.broadcastingClearnaceManagerMethod());
	}
	
	public BasicCommandInterpreter() {
		this(SingletonsCreator.bridgeSceneFactoryMethod(), SingletonsCreator.broadcastingClearnaceManagerMethod());
	}

	
	@ComponentWidth(componentWidth)
	public String getCommand() {
		return currentCommand;
		
	}
	
	public void setCommand(String commandLine) {
		String oldCommand = this.currentCommand;
		this.currentCommand = commandLine;
		this.errors = "";
		
		parse.setCommandText(commandLine);
		this.currentCommandRunnable = parse.getCommandObject();
		this.currentCommandRunnable.run();
		this.errors = parse.getErrors();

		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Command",oldCommand,this.currentCommand ));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errors));
		}
		
	}


	@Tags({"ErrorResilient"})
    @ComponentWidth(componentWidth)
	public String getErrors() {
		return this.errors;
	}
	
	
	@Tags({"asynchronousArthur"})
	public void asynchronousArthur() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.arthurAnimator, walkCommand, this.scene.getArthur(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}

	@Tags({"asynchronousGalahad"})
	public void asynchronousGalahad() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.galahadAnimator, walkCommand, this.scene.getGalahad(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousLancelot"})
	public void asynchronousLancelot() {
		final int distanceToWalk = 300;
		final int sleepTime = 50;
		Runnable animateCommand = new AnimationCommandObject(this.lancelotAnimator, walkCommand, this.scene.getLancelot(), distanceToWalk,sleepTime);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousRobin"})
	public void asynchronousRobin() {
		final int distanceToWalk = 260;
		Runnable animateCommand = new AnimationCommandObject(this.robinAnimator, walkCommand, this.scene.getRobin(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousGuard"})
	public void asynchronousGuard() {
		final int secondsToClap = 8;
		Runnable animateCommand = new AnimationCommandObject(this.guardAnimator, clapCommand, this.scene.getGuard(), secondsToClap);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"waitingArthur"})
	public void waitingArthur() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.arthurAnimator, this.manager, walkCommand, this.scene.getArthur(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingGalahad"})
	public void waitingGalahad() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.galahadAnimator, this.manager, walkCommand, this.scene.getGalahad(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingLancelot"})
	public void waitingLancelot() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.lancelotAnimator, this.manager, walkCommand, this.scene.getLancelot(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingRobin"})
	public void waitingRobin() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.robinAnimator, this.manager, walkCommand, this.scene.getRobin(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"start animation"})
	public void startAnimation() {
		this.manager.proceedAll();
	}
	
	@Tags({"lockstepArthur"})
	public void lockstepArthur() {
		final int distanceToMarch = 100;
		Runnable animateCommand = new LockstepCommandObject(this.arthurLock, marchCommand, this.scene.getArthur(), distanceToMarch);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepLancelot"})
	public void lockstepLancelot() {
		final int distanceToMarch = 100;
		Runnable animateCommand = new LockstepCommandObject(this.lancelotLock, marchCommand, this.scene.getLancelot(), distanceToMarch);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepGalahad"})
	public void lockstepGalahad() {
		final int distanceToMarch = 100;
		Runnable animateCommand = new LockstepCommandObject(this.galahadLock, marchCommand, this.scene.getGalahad(), distanceToMarch);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepRobin"})
	public void lockstepRobin() {
		final int distanceToMarch = 100;
		Runnable animateCommand = new LockstepCommandObject(this.robinLock, marchCommand, this.scene.getRobin(), distanceToMarch);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepGuard"})
	public void lockstepGuard() {
		final int timesToClap = 5;
		Runnable animateCommand = new LockstepCommandObject(this.guardLock, clapCommand, this.scene.getGuard(), timesToClap);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	

	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
	
	
}
