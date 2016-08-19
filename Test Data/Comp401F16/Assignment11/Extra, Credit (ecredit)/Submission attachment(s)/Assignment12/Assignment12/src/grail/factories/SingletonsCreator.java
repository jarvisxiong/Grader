package grail.factories;

import grail.avatar.Avatar;
import grail.commander.BasicCommandInterpreter;
import grail.commander.CommandInterpreter;
import grail.helpers.AScannerBean;
import grail.helpers.BasicCommandParser;
import grail.helpers.BasicTable;
import grail.helpers.BroadcastingClearanceManager;
import grail.helpers.ClearanceManager;
import grail.helpers.CommandParser;
import grail.helpers.Table;
import grail.interfaces.ScannerBean;
import grail.scene.BasicBridgeScene;
import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"SingletonsCreator"})
public class SingletonsCreator {
	private static ScannerBean scanner;
	private static CommandParser parser;
	private static BridgeScene scene;
	private static Table<String,Avatar> avatarList;
	private static CommandInterpreter commander;
	private static ClearanceManager manager;
	
	public SingletonsCreator() {
	}
	
	@Tags({"scannerFactoryMethod"})
	public static ScannerBean scannerFactoryMethod(){
		if (scanner == null) {
			scanner = new AScannerBean();
			return scanner;
		} else {
			return scanner;
		}

	}
	
	@Tags({"parserFactoryMethod"})
	public static CommandParser parserFactoryMethod(){
		if (parser == null) {
			parser = new BasicCommandParser();
			return parser;
		} else {
			return parser;
		}
		
	}
	
	@Tags({"bridgeSceneFactoryMethod"})
	public static BridgeScene bridgeSceneFactoryMethod(){
		if (scene == null) {
			scene = new BasicBridgeScene();
			return scene;
		} else {
			return scene;
		}
	}
	
	
	@Tags({"avatarTableFactoryMethod"})
	public static Table<String,Avatar> avatarTableFactoryMethod() {
		if (avatarList == null) {
			avatarList = new BasicTable<String,Avatar>();
			return avatarList;
		} else {
			return avatarList;
		}
	}
	
	@Tags({"commandInterpreterFactoryMethod"})
	public static CommandInterpreter commandInterpreterFactoryMethod() {
		if (commander == null) {
			commander = new BasicCommandInterpreter();
			return commander;
		} else {
			return commander;
		}
	}
	
	
	@Tags({"broadcastingClearnaceManagerMethod"})
	public static ClearanceManager broadcastingClearnaceManagerMethod() {
		if (manager == null) {
			manager = new BroadcastingClearanceManager();
			return manager;
		} else {
			return manager;
		}
	}
	
	static {
		scanner = new AScannerBean();
		avatarList = new BasicTable<String,Avatar>();
		scene = new BasicBridgeScene();
		manager = new BroadcastingClearanceManager();
		parser = new BasicCommandParser();
		commander = new BasicCommandInterpreter();
	}
}
