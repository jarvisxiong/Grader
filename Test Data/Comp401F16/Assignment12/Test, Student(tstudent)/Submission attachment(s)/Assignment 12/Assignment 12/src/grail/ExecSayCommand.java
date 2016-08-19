package grail;

import mp.BridgeScene;
import util.annotations.Tags;

@Tags({"SayCommand"})
public class ExecSayCommand implements ExecutableSay {
	BridgeScene theScene;
	String say;
	public ExecSayCommand (BridgeScene aBridgeScene, String toSay) {
		theScene = aBridgeScene;
		say = toSay;
	}
	
	public void run() {
		theScene.sayString(say);
	}

}
