package mp;

import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
@Tags({"Gorge"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"LeftBank","RightBank", "TopBridge","BottomBridge"})

public class AGorgeScene implements GorgeScene{
	Line leftBank, rightBank, topBridge, bottomBridge;
	
	public AGorgeScene() {}
	public AGorgeScene(int initX, int initY, int initGorgeWidth, int initGorgeHeight, int initBridgeWidth, int initBridgeHeight) {
		leftBank = new LineShape(initX, 0, 0, initGorgeHeight);
		rightBank = new LineShape(initX + initGorgeWidth, 0, 0, initGorgeHeight);
		topBridge = new LineShape(initX, initY, initBridgeWidth, 0);
		bottomBridge = new LineShape(initX, initY + initBridgeHeight, initBridgeWidth, 0);
	}
	
	public Line getLeftBank() {
		return leftBank;
	}
	public Line getRightBank() {
		return rightBank;
	}
	public Line getTopBridge() {
		return topBridge;
	}
	public Line getBottomBridge() {
		return bottomBridge;
	}
	
}
