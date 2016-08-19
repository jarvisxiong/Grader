package main;

import bus.uigen.OEFrame;
import mp.Avatar;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"CommandInterpreter"})
@PropertyNames({"Command"})
@EditablePropertyNames({"Command"})
public class CommandInterpreter implements Command {
	String[] scan;
	String[] parsedString;
	String output, token1, token2, token3, token4, tokenAvatar;
	BridgeSceneInterface scene;
	ScannerBeanInterface scannerBean;
	int x, y;
	Table table;
	OEFrame frame;
	
	public CommandInterpreter(BridgeSceneInterface newScene, ScannerBeanInterface newScannerBean){
		// Establishing Table
		//System.out.println("Establishing Table");
		table = new ATable();
					
		// Associating keys with values
		table.put("arthur", (Object) newScene.getArthur());
		//System.out.println("<arthur, "+table.get("arthur") + "> Using the term arthur to retrieve Object Arthur");
		table.put("galahad", (Object) newScene.getGalahad());
		//System.out.println("<galahad, " + table.get("galahad") + "> Using the term galahad to retrieve Object Galahad");
		table.put("lancelot", (Object) newScene.getLancelot());
		//System.out.println("<lancelot, " + table.get("lancelot") + "> Using the term lancelot to retrieve Object Lancelot");
		table.put("robin", (Object) newScene.getRobin());
		//System.out.println("<robin, " + table.get("robin") + "> Using the term robin to retrieve Object Robin");
		table.put("guard",(Object) newScene.getGuard());
		//System.out.println("<guard, " + table.get("guard") + "> Using the term guard to retrieve Object Guard");
		
		scene = newScene;
		scannerBean = newScannerBean;
		this.scan = scannerBean.getTokens();
		this.parsedString = scannerBean.getParse();
		//output = scan(this.scan);
	}
	
	@Tags({"parseMove"})
	public Thread setMoveT(String output, int moveX, int moveY){
		Object moveAvatar = table.get(output);
		Thread move = new Thread(new MoveCommand(((Avatar) moveAvatar), moveX, moveY));
		return move;
	}
	
	public void setMove(String output, int moveX, int moveY){
		Object moveAvatar = table.get(output);
		((Avatar) moveAvatar).animatedMove(moveX, moveY);
	}
	
	@Visible(false)
	public String[] getCommand() {return this.parsedString;}
	
	public String scan(String[] input){
		
		String scanned = "";
		for(int i = 0; i < input.length - 1; i++){ // Say Command must be 2 tokens long 
			// Checks each token within array for "say"
			token1 = input[i].toLowerCase(); // "say token" or "move token"
			token2 = input[i+1].toLowerCase();// "quote token"
			if("say token".equals(token1)){ // Does token1 == "say token"
				// Check the next token to see if is a quote token
				if("quote token".equals(token2)){ // Does token2 == "quote token"
					scanned = scannerBean.getParse()[i+1]; // Build String "scanned" with actual input
				}
			}else if("move token".equals(token1) && i < input.length - 3){
				tokenAvatar = scannerBean.getParse()[i+1];
				token3 = input[i+2].toLowerCase();
				token4 = input[i+3].toLowerCase();
				if("arthur".equalsIgnoreCase(tokenAvatar)){
					if("number token".equals(token3)){
						scanned = scannerBean.getParse()[i+1];
						if("number token".equals(token4)){
							String moveX = scannerBean.getParse()[i+2];
							x = Integer.parseInt(moveX);
							String moveY = scannerBean.getParse()[i+3];
							y = Integer.parseInt(moveY);
						}
					}
				}else if("galahad".equalsIgnoreCase(tokenAvatar)){
					if("number token".equals(token3)){
						scanned = scannerBean.getParse()[i+1];
						if("number token".equals(token4)){
							String moveX = scannerBean.getParse()[i+2];
							x = Integer.parseInt(moveX);
							String moveY = scannerBean.getParse()[i+3];
							y = Integer.parseInt(moveY);
						}
					}
				}else if("lancelot".equalsIgnoreCase(tokenAvatar)){
					if("number token".equals(token3)){
						scanned = scannerBean.getParse()[i+1];
						if("number token".equals(token4)){
							String moveX = scannerBean.getParse()[i+2];
							x = Integer.parseInt(moveX);
							String moveY = scannerBean.getParse()[i+3];
							y = Integer.parseInt(moveY);
						}
					}
				}else if("robin".equalsIgnoreCase(tokenAvatar)){
					if("number token".equals(token3)){
						scanned = scannerBean.getParse()[i+1];
						if("number token".equals(token4)){
							String moveX = scannerBean.getParse()[i+2];
							x = Integer.parseInt(moveX);
							String moveY = scannerBean.getParse()[i+3];
							y = Integer.parseInt(moveY);
						}
					}
				}else if("guard".equalsIgnoreCase(tokenAvatar)){
					if("number token".equals(token3)){
						scanned = scannerBean.getParse()[i+1];
						if("number token".equals(token4)){
							String moveX = scannerBean.getParse()[i+2];
							x = Integer.parseInt(moveX);
							String moveY = scannerBean.getParse()[i+3];
							y = Integer.parseInt(moveY);
						}
					}
				}
			}
		}
		return scanned;
	}
	
	public void setCommand(String Command) {
		scannerBean.setScannedString(Command);
		String[] tokens = scannerBean.getTokens(); // returns Tokens Array
		String[] parse = scannerBean.getParse(); // returns Parsed String[]
		
		// This calls method "scan" to parse and return a string that is either a 
		String output = scan(tokens); // scans Tokens Array and outputs a command string
		
		if("arthur".equalsIgnoreCase(output)){
			Thread moveArthur = setMoveT(output, x, y);
			moveArthur.start();
		}else if("galahad".equalsIgnoreCase(output)){
			Thread moveGalahad = setMoveT(output, x, y);	
			moveGalahad.start();
		}else if("lancelot".equalsIgnoreCase(output)){
			Thread moveLancelot = setMoveT(output, x, y);	
			moveLancelot.start();
		}else if("robin".equalsIgnoreCase(output)){
			Thread moveRobin = setMoveT(output, x, y);
			moveRobin.start();
		}else if("guard".equalsIgnoreCase(output)){
			Thread moveGuard = 	setMoveT(output, x, y);	
			moveGuard.start();
		}
		// This allows Avatar to speak
		//scene.getGuard().getText().setText(output);
		Thread speech = setSay();
		speech.start();
	}
	
	@Tags({"parseSay"})
	public Thread setSay(){
		Thread say = new Thread(new SayCommand(scene,output));
		return say;
	}
	
	@Tags({"asynchronousArthur"})
	public void asynArthur(){
		Avatar arthurAv = scene.getArthur();
		Runnable arthur = new AnimatingCommands(arthurAv);
		Thread art = new Thread(arthur);
		art.start();
	}
	
	@Tags({"asynchronousGalahad"})
	public void asynGalahad(){
		Runnable galahad = new AnimatingCommands(scene.getGalahad());
		Thread gal = new Thread(galahad);
		gal.start();
	}
	
	@Tags({"asynchronousLancelot"})
	public void asynLancelot(){
		Runnable lancelot = new AnimatingCommands(scene.getLancelot());
		Thread lan = new Thread(lancelot);
		lan.start();
	}
	
	@Tags({"asynchronousRobin"})
	public void asynRobin(){
		Runnable robin = new AnimatingCommands(scene.getRobin());
		Thread rob = new Thread(robin);
		rob.start();
	}
	
}
