package mp.bean;

import mp.tokens.AValueToken;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;

import mp.bean.commands.MoveCommand;
import mp.bean.commands.SayCommand;
import mp.bean.animation.ABroadcastingClearanceManager;
import mp.bean.animation.AnAvatarClapAnimator;
import mp.bean.animation.AnAvatarProceedClapAnimator;
import mp.bean.commands.AvatarCommand;
import mp.bean.commands.CoordinatedAnimationCommand;
import mp.bean.animation.AnAvatarSquareDanceAnimator;
import mp.bean.animation.AvatarAnimator;
import mp.bean.animation.BroadcastingClearanceManager;
import mp.bean.animation.CoordinatedAnimator;
import mp.tokens.ABasicToken;
import mp.tokens.AMinusToken;
import mp.tokens.ANumToken;
import mp.tokens.APlusToken;
import mp.tokens.AQuoteToken;
import mp.tokens.MoveToken;
import mp.tokens.SayToken;
import mp.tokens.Token;
import mp.ui.BridgeScene;
import mp.ui.mvc.ACommandInterpreterView;
import mp.ui.mvc.APropertyListenerSupport;
import mp.ui.mvc.PropertyListenerSupport;
import mp.ui.AnAvatar;
import mp.ui.Avatar;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"CommandInterpreter", "SignedMove", "ErrorResilient"})
@PropertyNames({"Command", "Table", "Errors"})
@EditablePropertyNames({"Command"})

public class ACommandInterpreter implements CommandInterpreter {

	private static final String AVATAR_ANIMATOR_THREAD_NAME = "Avatar Animator Thread";
	private static final int STEP_SIZE = 10, CLAP_SIZE = 12, PAUSE_TIME = 20, CLAP_PAUSE_TIME = 2200;
	Table<String, Avatar> avatars;
	Scanner scanner;
	BridgeScene scene;
	Parser parser;
	String error;
	PropertyListenerSupport propertyListenerSupport =
			new APropertyListenerSupport();
	int progress, oldProgress, threadNumber = 0;
	AvatarAnimator sdAnimatorArthur, sdAnimatorGalahad, sdAnimatorLancelot, sdAnimatorRobin;
	AvatarAnimator coordAnimatorArthur, coordAnimatorGalahad, coordAnimatorLancelot, coordAnimatorRobin;
	AvatarAnimator gcAnimatorGuard;
	AvatarAnimator coordClapAnimator;
	BroadcastingClearanceManager cManager;
	Table<String, Runnable> commandEnvironment;
	
	public ACommandInterpreter(BridgeScene initBridgescene, BroadcastingClearanceManager clearanceManager) {
		scene = initBridgescene;
		cManager = clearanceManager;
		commandEnvironment = new ATable<String, Runnable>();
		parser = new AParser(scene, commandEnvironment, cManager);
		parser.addPropertyChangeListener(this);
		
		sdAnimatorArthur = new AnAvatarSquareDanceAnimator();
		sdAnimatorGalahad = new AnAvatarSquareDanceAnimator();
		sdAnimatorLancelot = new AnAvatarSquareDanceAnimator();
		sdAnimatorRobin = new AnAvatarSquareDanceAnimator();
		
		gcAnimatorGuard = new AnAvatarClapAnimator();
		
		coordAnimatorArthur = new CoordinatedAnimator();
		coordAnimatorGalahad = new CoordinatedAnimator();
		coordAnimatorLancelot = new CoordinatedAnimator();
		coordAnimatorRobin = new CoordinatedAnimator();
		
		coordClapAnimator = new AnAvatarProceedClapAnimator();
		
		avatars = new ATable<String, Avatar>();
		avatars.put("arthur", scene.getArthur());
		avatars.put("galahad", scene.getGalahad());
		avatars.put("lancelot", scene.getLancelot());
		avatars.put("robin", scene.getRobin());
		avatars.put("guard", scene.getGuard());
	}
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
			propertyListenerSupport.addElement(listener); 
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Error")) {
			setErrors((String) evt.getNewValue());
		}
	}
	@Row(0)
	@ComponentWidth(250)
	public void setCommand(String commandline) {
		parser.setCommandText(commandline);
		parser.getCommandObject().run();
	}
	@Visible(true)
	public String getErrors() {
		return error;
	}
	private void setErrors(String text) {
		String oldVal = error;
		error = text;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Error", oldVal, error));
	}
	@Visible(false)
	public Table getTable() {
		return avatars;
	}
	@Tags({"asynchronousArthur"})
	public void asyncArthur() {
		Thread thread = new Thread(new AvatarCommand(null, sdAnimatorArthur, (Avatar) avatars.get("arthur"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"asynchronousGalahad"})
	public void asyncGalahad() {
		Thread thread = new Thread(new AvatarCommand(null, sdAnimatorGalahad, (Avatar) avatars.get("galahad"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"asynchronousLancelot"})
	public void asyncLancelot() {
		Thread thread = new Thread(new AvatarCommand(null, sdAnimatorLancelot, (Avatar) avatars.get("lancelot"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"asynchronousRobin"}) 
	public void asyncRobin() {
		Thread thread = new Thread(new AvatarCommand(null, sdAnimatorRobin, (Avatar) avatars.get("robin"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"asynchronousGuard"}) 
	public void asyncGuard() {
		Thread thread = new Thread(new AvatarCommand(null, gcAnimatorGuard, (Avatar) avatars.get("guard"), CLAP_SIZE, CLAP_PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"waitingArthur"}) 
	public void waitingArthur() {
		Thread thread = new Thread(new AvatarCommand(cManager, sdAnimatorArthur, (Avatar) avatars.get("arthur"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"waitingGalahad"}) 
	public void waitingGalahad() {
		Thread thread = new Thread(new AvatarCommand(cManager, sdAnimatorGalahad, (Avatar) avatars.get("galahad"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"waitingLancelot"}) 
	public void waitingLancelot() {
		Thread thread = new Thread(new AvatarCommand(cManager, sdAnimatorLancelot, (Avatar) avatars.get("lancelot"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"waitingRobin"}) 
	public void waitingRobin() {
		Thread thread = new Thread(new AvatarCommand(cManager, sdAnimatorRobin, (Avatar) avatars.get("robin"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"startAnimation"})
	public void startAnimation() {
		cManager.proceedAll();
	}
	@Tags({"lockstepArthur"})
	public void lockstepArthur() {
		Thread thread = new Thread(new CoordinatedAnimationCommand(cManager, coordAnimatorArthur, (Avatar) avatars.get("arthur"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"lockstepGalahad"})
	public void lockstepGalahad() {
		Thread thread = new Thread(new CoordinatedAnimationCommand(cManager, coordAnimatorGalahad, (Avatar) avatars.get("galahad"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"lockstepLancelot"})
	public void lockstepLancelot() {
		Thread thread = new Thread(new CoordinatedAnimationCommand(cManager, coordAnimatorLancelot, (Avatar) avatars.get("lancelot"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"lockstepRobin"})
	public void lockstepRobin() {
		Thread thread = new Thread(new CoordinatedAnimationCommand(cManager, coordAnimatorRobin, (Avatar) avatars.get("robin"), STEP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " " + threadNumber);
		thread.start();
	}
	@Tags({"lockstepGuard"})
	public void lockstepGuard() {
		Thread thread = new Thread(new CoordinatedAnimationCommand(cManager, coordClapAnimator, (Avatar) avatars.get("guard"), CLAP_SIZE, PAUSE_TIME));
		threadNumber++;
		thread.setName(AVATAR_ANIMATOR_THREAD_NAME + " guard " + threadNumber);
		thread.start();
	}
}
