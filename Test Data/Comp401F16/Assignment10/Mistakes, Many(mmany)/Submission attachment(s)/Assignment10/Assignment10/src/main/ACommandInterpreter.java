package main;

import java.util.ArrayList;

import mp.commandobjects.AGuardAnimator;
import mp.commandobjects.AnAnimator;
import mp.commandobjects.AnimatingCommandObject;
import mp.commandobjects.MoveCommandObject;
import mp.commandobjects.SayCommandObject;
import mp.commands.MoveCommand;
import mp.commands.SayCommand;
import mp.helpers.ATable;
import mp.helpers.Table;
import mp.shapes.Avatar;
import mp.tokens.MinusToken;
import mp.tokens.NumberToken;
import mp.tokens.QuoteToken;
import mp.tokens.SignToken;
import mp.tokens.TokenHistory;
import mp.tokens.WordToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;

@Tags({"SignedMove", "ErrorResilient", "Command Interpreter"})
@StructurePattern("Bean Pattern")
@PropertyNames({"errors", "table"})
@EditablePropertyNames({"Command"})
public class ACommandInterpreter implements CommandInterpreter{
	private ScannerBean scannerBean;
	private BridgeScene bridgeScene;
	private final String errorText = "ERROR - Invalid Command : ";
	
	private ArrayList<String> errors;
	
	private Table table;
	
	public ACommandInterpreter(ScannerBean scannerBean, BridgeScene bridgeScene){
		this.scannerBean = scannerBean;
		this.bridgeScene = bridgeScene;
		errors = new ArrayList<String>();
		
		table = new ATable();
		table.put("arthur", bridgeScene.getArthur());
		table.put("lancelot", bridgeScene.getLancelot());
		table.put("guard", bridgeScene.getGuard());
		table.put("galahad", bridgeScene.getGalahad());
		table.put("robin", bridgeScene.getRobin());
	}
	
	@Tags({"parseSay"})
	private Runnable parseSay(TokenHistory tokenHistory, String command){
		if(tokenHistory.get(1) instanceof QuoteToken && bridgeScene.getOccupied()){
			return new SayCommandObject(bridgeScene, tokenHistory.get(1).getInput());
		}else{
			errors.add(errorText + command);
			return null;
		}
	}
	
	@Tags({"parseMove"})
	private Runnable parseMove(TokenHistory tokenHistory, String command){
		if(tokenHistory.get(1) instanceof WordToken && tokenHistory.get(2) instanceof NumberToken
				&& tokenHistory.get(2+1) instanceof NumberToken){
			String avatarKey = ((WordToken) tokenHistory.get(1)).getValue();
			int xOffset = ((NumberToken) tokenHistory.get(2)).getValue();
			int yOffset = ((NumberToken) tokenHistory.get(2+1)).getValue();
			
			return new MoveCommandObject((Avatar) table.get(avatarKey), xOffset, yOffset);
		}else if(tokenHistory.get(1) instanceof WordToken && tokenHistory.get(2) instanceof SignToken
				&& tokenHistory.get(2+1) instanceof NumberToken && tokenHistory.get(2+2) instanceof SignToken
				&& tokenHistory.get(2+2+1) instanceof NumberToken){
			String avatarKey = ((WordToken) tokenHistory.get(1)).getValue();
			int xOffset = ((NumberToken) tokenHistory.get(2+1)).getValue();
			int yOffset = ((NumberToken) tokenHistory.get(2+2+1)).getValue();
			
			if(tokenHistory.get(2) instanceof MinusToken){
				xOffset = -xOffset;
			}
			if(tokenHistory.get(2+2) instanceof MinusToken){
				yOffset = -yOffset;
			}
			
			return new MoveCommandObject((Avatar) table.get(avatarKey), xOffset, yOffset);
		}else{
			errors.add(errorText + command);
			return null;
		}
	}
	
	@Override
	public void setCommand(String command) {
		scannerBean.setScannedString(command);
		TokenHistory tokenHistory = scannerBean.getTokenList();
		
		if(tokenHistory.get(0) instanceof MoveCommand){
			parseMove(tokenHistory, command).run();
		}else if(tokenHistory.get(0) instanceof SayCommand){
			parseSay(tokenHistory, command).run();
		}else{
			errors.add(errorText + command);
		}
	}

	@Override
	public Table getTable(){
		return table;
	}

	@Override
	public ArrayList<String> getErrors() {
		return errors;
	}

	@Tags({"asynchronousArthur"})
	@Override
	public void animateArthur() {
		Thread thread = new Thread(new AnimatingCommandObject(
					new AnAnimator(),
					bridgeScene.getArthur()
				));
		thread.start();
	}

	@Tags({"asynchronousLancelot"})
	@Override
	public void animateLancelot() {
		Thread thread = new Thread(new AnimatingCommandObject(
				new AnAnimator(),
				bridgeScene.getLancelot()
			));
		thread.start();
	}

	@Tags({"asynchronousGalahad"})
	@Override
	public void animateGalahad() {
		Thread thread = new Thread(new AnimatingCommandObject(
				new AnAnimator(),
				bridgeScene.getGalahad()
			));
		thread.start();
	}

	@Tags({"asynchronousRobin"})
	@Override
	public void animateRobin() {
		Thread thread = new Thread(new AnimatingCommandObject(
				new AnAnimator(),
				bridgeScene.getRobin()
			));
		thread.start();
	}
	
	@Tags({"asynchronousGuard"})
	@Override
	public void animateGuard(){
		Thread thread = new Thread(new AnimatingCommandObject(
				new AGuardAnimator(),
				bridgeScene.getGuard()
			));
		thread.start();
	}
}
