package main;

import mp.commandobjects.AParser;
import mp.commandobjects.Parser;
import mp.helpers.ABroadcastingClearanceManager;
import mp.helpers.ATable;
import mp.helpers.BroadcastingClearanceManager;
import mp.helpers.Table;
import mp.shapes.Avatar;
import util.annotations.Tags;

@Tags({"SingletonsCreator"})
public class ASingletonsCreator {
	private static ScannerBean scannerBean;
	private static Parser parser;
	private static BridgeScene scene;
	private static Table<String, Avatar> avatarTable;
	private static CommandInterpreter cmd;
	private static BroadcastingClearanceManager manager;
	
	@Tags({"scannerFactoryMethod"})
	public static ScannerBean getScannerBean(){
		if(scannerBean == null){
			scannerBean = new AScannerBean();
		}
		return scannerBean;
	}
	
	@Tags({"parserFactoryMethod"})
	public static Parser getParser(){
		if(parser == null){
			parser = new AParser(ASingletonsCreator.getBridgeScene());
		}
		return parser;
	}
	
	@Tags({"bridgeSceneFactoryMethod"})
	public static BridgeScene getBridgeScene(){
		if(scene == null){
			scene = new ABridgeScene();
		}
		return scene;
	}
	
	@Tags({"avatarTableFactoryMethod"})
	public static Table<String, Avatar> getAvatarTable(){
		if(avatarTable == null){
			avatarTable = new ATable<String, Avatar>();
			avatarTable.put("arthur", scene.getArthur());
			avatarTable.put("lancelot", scene.getLancelot());
			avatarTable.put("guard", scene.getGuard());
			avatarTable.put("galahad", scene.getGalahad());
			avatarTable.put("robin", scene.getRobin());
		}
		return avatarTable;
	}
	
	@Tags({"commandInterpreterFactoryMethod"})
	public static CommandInterpreter getCommandInterpreter(){
		if(cmd == null){
			cmd = new ACommandInterpreter(ASingletonsCreator.getClearanceManager(), ASingletonsCreator.getBridgeScene());
		}
		return cmd;
	}
	
	@Tags({"broadcastingClearanceManagerMethod"})
	public static BroadcastingClearanceManager getClearanceManager(){
		if(manager == null){
			manager = new ABroadcastingClearanceManager();
		}
		return manager;
	}
}
