package mp.command;

import mp.Scene.Avatar;
import util.annotations.Tags;

@Tags({"MoveCommand"})
public class AMoveCommand implements MoveCommand{
	Avatar theMan;
	int x;
	int y;
	public AMoveCommand(Avatar aMan,int deltaX,int deltaY){
		theMan=aMan;
		x=deltaX;
		y=deltaY;
	}
	public void run(){
		theMan.moveThis(x, y);
	}
}
