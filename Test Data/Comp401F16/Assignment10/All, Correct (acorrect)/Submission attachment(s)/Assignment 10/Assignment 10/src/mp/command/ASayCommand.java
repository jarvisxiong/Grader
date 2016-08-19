package mp.command;

import mp.Scene.BridgeScene;
import util.annotations.Tags;

@Tags({"SayCommand"})
public class ASayCommand implements SayCommand{
		String say;
		BridgeScene aScene;
		public ASayCommand(BridgeScene newScene,String text){
			aScene=newScene;
			say=text;
		}
		public void run(){
			aScene.sayScene(say);
		}
}
