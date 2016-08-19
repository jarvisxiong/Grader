package mp;

import mp.Locatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Gorge"})
@PropertyNames({"x","y","G1","G2","bridge"})
//@EditablePropertyNames({"x","y"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AGorge extends Locatable implements Gorge {
	Line G1, G2;
	Rectangle bridge;
	public AGorge(){
		G1 = new ALine(900,0,800,900);
		G2 = new ALine(950,0,800,950);
		bridge = new ARectangle(875,350,100,50);
	}
	
	public Line getG1(){return G1;}
	public Line getG2(){return G2;}
	public Rectangle getBridge(){return bridge;}
}
