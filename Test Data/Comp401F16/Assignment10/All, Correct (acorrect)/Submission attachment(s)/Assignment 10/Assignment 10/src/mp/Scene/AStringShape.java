package mp.Scene;

import java.awt.Color;
import java.beans.PropertyChangeEvent;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@PropertyNames({"X","Y","Color","Text"})
@StructurePattern(StructurePatternNames.STRING_PATTERN) 
public class AStringShape extends ALocatable implements StringShape{
	String text;
	//PropertyListenerSupport aListener= new APropertyListenerSupport();
	public AStringShape(int intX, int intY, String input){
		x=intX;
		y=intY;
		text= input;
	}
	
	public Color getColor(){return Color.yellow;}
	public String getText() {return text;}
	public void setText(String newVal) {
		String oldVal=text;
		text = newVal;
		aListener.notifyAllListeners(new PropertyChangeEvent(this,"Text",oldVal,newVal));
		}
}
