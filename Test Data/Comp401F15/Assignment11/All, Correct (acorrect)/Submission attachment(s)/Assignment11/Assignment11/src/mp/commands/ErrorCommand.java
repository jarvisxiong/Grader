package mp.commands;

import grail.shape.BridgeScene;
import grail.shape.BridgeSceneInterface;

public class ErrorCommand implements Runnable {
	BridgeSceneInterface theScene;
	public ErrorCommand(BridgeSceneInterface aScene){
		theScene = aScene;
	}
	
	@Override
	public void run() {
		theScene.getArthur().getText().setText("WHAT HAVE YOU DONE?!");
		theScene.getRobin().getText().setText("WHAT HAVE YOU DONE?!");
		theScene.getLancelot().getText().setText("WHAT HAVE YOU DONE?!");
		theScene.getGalahad().getText().setText("WHAT HAVE YOU DONE?!");
		theScene.getGuard().getText().setText("WHAT HAVE YOU DONE?!");
	}

}
