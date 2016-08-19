package main;

import mp.commandobjects.AGuardAnimator;
import mp.commandobjects.AParser;
import mp.commandobjects.AdvancedAnimatingCommandObject;
import mp.commandobjects.AnAnimator;
import mp.commandobjects.AnimatingCommandObject;
import mp.commandobjects.Parser;
import mp.helpers.BroadcastingClearanceManager;
import util.annotations.EditablePropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;

@Tags({"SignedMove", "ErrorResilient", "Command Interpreter"})
@StructurePattern("Bean Pattern")
@EditablePropertyNames({"Command"})
public class ACommandInterpreter implements CommandInterpreter{
	private BridgeScene bridgeScene;
	private Parser parser;
	
	private AnAnimator animateArthur;
	private AnAnimator animateLancelot;
	private AnAnimator animateGalahad;
	private AnAnimator animateRobin;
	
	private AGuardAnimator animateGuard;
	
	private BroadcastingClearanceManager manager;
	
	public ACommandInterpreter(BroadcastingClearanceManager manager, BridgeScene bridgeScene){
		this.bridgeScene = bridgeScene;
		parser = ASingletonsCreator.getParser();
		
		animateArthur = new AnAnimator();
		animateLancelot = new AnAnimator();
		animateGalahad = new AnAnimator();
		animateRobin = new AnAnimator();
		
		this.manager = manager;
		
		animateGuard = new AGuardAnimator();
	}
	
	@Override
	public void setCommand(String command) {
		parser.setCommandText(command);
		parser.getCommandObject().run();
	}

	@Tags({"asynchronousArthur"})
	@Override
	public void animateArthur() {
		Thread thread = new Thread(new AnimatingCommandObject(
					animateArthur,
					bridgeScene.getArthur()
				));
		thread.start();
	}

	@Tags({"asynchronousLancelot"})
	@Override
	public void animateLancelot() {
		Thread thread = new Thread(new AnimatingCommandObject(
				animateLancelot,
				bridgeScene.getLancelot()
			));
		thread.start();
	}

	@Tags({"asynchronousGalahad"})
	@Override
	public void animateGalahad() {
		Thread thread = new Thread(new AnimatingCommandObject(
				animateGalahad,
				bridgeScene.getGalahad()
			));
		thread.start();
	}

	@Tags({"asynchronousRobin"})
	@Override
	public void animateRobin() {
		Thread thread = new Thread(new AnimatingCommandObject(
				animateRobin,
				bridgeScene.getRobin()
			));
		thread.start();
	}
	
	@Tags({"asynchronousGuard"})
	@Override
	public void animateGuard(){
		Thread thread = new Thread(new AnimatingCommandObject(
				animateGuard,
				bridgeScene.getGuard()
			));
		thread.start();
	}

	@Tags({"waitingArthur"})
	@Override
	public void waitingArthur() {
		Thread thread = new Thread(new AdvancedAnimatingCommandObject(
				animateArthur,
				bridgeScene.getArthur(),
				manager
			));
		thread.start();
	}

	@Tags({"waitingGalahad"})
	@Override
	public void waitingGalahad() {
		Thread thread = new Thread(new AdvancedAnimatingCommandObject(
				animateGalahad,
				bridgeScene.getGalahad(),
				manager
			));
		thread.start();
	}

	@Tags({"waitingRobin"})
	@Override
	public void waitingRobin() {
		Thread thread = new Thread(new AdvancedAnimatingCommandObject(
				animateRobin,
				bridgeScene.getRobin(),
				manager
			));
		thread.start();
	}

	@Tags({"waitingLancelot"})
	@Override
	public void waitingLancelot() {
		Thread thread = new Thread(new AdvancedAnimatingCommandObject(
				animateLancelot,
				bridgeScene.getLancelot(),
				manager
			));
		thread.start();
	}

	@Tags({"startAnimation"})
	@Override
	public void startAnimation() {
		manager.proceedAll();
	}
}
