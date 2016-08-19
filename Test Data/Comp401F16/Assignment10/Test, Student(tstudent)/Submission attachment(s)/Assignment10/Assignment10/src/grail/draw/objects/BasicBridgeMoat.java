package grail.draw.objects;

import java.beans.PropertyChangeListener;

import grail.draw.interfaces.BridgeMoat;
import grail.draw.interfaces.RotatingShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "MoatLeft", "MoatRight", "BridgeBottom", "BridgeTop","PlankOne", "PlankTwo", "PlankThree", "PlankFour", "PlankFive", "PlankSix", "FallZoneX", "FallZoneY"})
@EditablePropertyNames({"X", "Y", "Width", "Height"})
public class BasicBridgeMoat extends BasicMutableShape implements BridgeMoat{
	private RotatingShape moatLeft, moatRight, bridgeTop, bridgeBottom, plankOne, plankTwo, plankThree, plankFour, plankFive, plankSix;
	
	public BasicBridgeMoat(int x, int y) {
		super();
		final int defaultHeight = 400;
		final int defaultWidth = 200;
		this.x = x;
		this.y = y;
		this.height = defaultHeight;
		this.width = defaultWidth;
		this.moatLeft = new LineRotatingShape();
		this.moatRight = new LineRotatingShape();
		this.bridgeTop = new LineRotatingShape();
		this.bridgeBottom = new LineRotatingShape();
		this.plankOne = new LineRotatingShape();
		this.plankTwo = new LineRotatingShape();
		this.plankThree = new LineRotatingShape();
		this.plankFour = new LineRotatingShape();
		this.plankFive = new LineRotatingShape();
		this.plankSix = new LineRotatingShape();
		
		buildBridgeMoat(this.x, this.y,this.width, this.height);
	}
	
	private void buildBridgeMoat(int xLocation, int yLocation, int widthInt, int heightInt) {
		final double width = widthInt;
		final double height = heightInt;
		final double halfWidth = ((double)width)/2;
		final int moatRightStartX =(int) (xLocation + width);
		final int moatLeftStartX = (int) (xLocation + width/2);
		final int moatRightStartY = yLocation;
		final int moatLeftStartY = yLocation;
		final double moatRadius = (Math.sqrt(halfWidth * halfWidth + height * height));
		final double moatAngle = (Math.PI - Math.atan(height/halfWidth));
		this.moatLeft.setX(moatLeftStartX);
		this.moatLeft.setY(moatLeftStartY);
		this.moatLeft.setRadius(moatRadius);
		this.moatLeft.setAngle(moatAngle);
		this.moatRight.setX(moatRightStartX);
		this.moatRight.setY(moatRightStartY);
		this.moatRight.setRadius(moatRadius);
		this.moatRight.setAngle(moatAngle);
		
		
		final double widthDivision = 6;
		final double heightDivision = 3;
		final double sixthOfWidth = width/widthDivision;
		final double thirdOfHeight = height/heightDivision;
		final double fiveSixthOfWidth = 5 * sixthOfWidth;
		final int bridgeTopX = (int) (xLocation + (sixthOfWidth));
		final int bridgeTopY = (int) (yLocation + (thirdOfHeight));
		final double bridgeTopRadius = (fiveSixthOfWidth);
		final double bridgeTopAngle = 0;
		this.bridgeTop.setX(bridgeTopX);
		this.bridgeTop.setY(bridgeTopY);
		this.bridgeTop.setRadius(bridgeTopRadius);
		this.bridgeTop.setAngle(bridgeTopAngle);
		
		final double twoThirdsOfHeight = 2 * thirdOfHeight;
		final int bridgeBottomX = (xLocation);
		final int bridgeBottomY = (int) (yLocation + (twoThirdsOfHeight));
		final double bridgeBottomRadius = (fiveSixthOfWidth);
		final double bridgeBottomAngle = 0;
		this.bridgeBottom.setX(bridgeBottomX);
		this.bridgeBottom.setY(bridgeBottomY);
		this.bridgeBottom.setRadius(bridgeBottomRadius);
		this.bridgeBottom.setAngle(bridgeBottomAngle);
		
	
		
		int plankNumber = 0;
		int plankLineX =(int) (xLocation + plankNumber * (sixthOfWidth));
		final int plankLineY =(int) (yLocation + (twoThirdsOfHeight));
		final double plankLineRadius = (Math.sqrt(Math.pow(thirdOfHeight, 2) + Math.pow(sixthOfWidth, 2)));
		final double plankAngle = (-(Math.atan((thirdOfHeight)/(sixthOfWidth))));
		this.plankOne.setX(plankLineX);
		this.plankOne.setY(plankLineY);
		this.plankOne.setRadius(plankLineRadius);
		this.plankOne.setAngle(plankAngle);
		
		plankNumber++;
		plankLineX = (int)(xLocation + plankNumber * (sixthOfWidth));
		this.plankTwo.setX(plankLineX);
		this.plankTwo.setY(plankLineY);
		this.plankTwo.setRadius(plankLineRadius);
		this.plankTwo.setAngle(plankAngle);

		plankNumber++;
		plankLineX = (int)(xLocation + plankNumber * (sixthOfWidth));
		this.plankThree.setX(plankLineX);
		this.plankThree.setY(plankLineY);
		this.plankThree.setRadius(plankLineRadius);
		this.plankThree.setAngle(plankAngle);
		
		plankNumber++;
		plankLineX = (int) (xLocation + plankNumber * (sixthOfWidth));
		this.plankFour.setX(plankLineX);
		this.plankFour.setY(plankLineY);
		this.plankFour.setRadius(plankLineRadius);
		this.plankFour.setAngle(plankAngle);
		
		plankNumber++;
		plankLineX = (int)(xLocation + plankNumber * (sixthOfWidth));
		this.plankFive.setX(plankLineX);
		this.plankFive.setY(plankLineY);
		this.plankFive.setRadius(plankLineRadius);
		this.plankFive.setAngle(plankAngle);
		
		plankNumber++;
		plankLineX = (int)(xLocation + plankNumber * (sixthOfWidth));
		this.plankSix.setX(plankLineX);
		this.plankSix.setY(plankLineY);
		this.plankSix.setRadius(plankLineRadius);
		this.plankSix.setAngle(plankAngle);
			
	}

	public BasicBridgeMoat() {
		this(0,0);
	}
	
	public void setHeight(int newHeight) {
		this.height = newHeight;
		buildBridgeMoat(this.x, this.y, this.width, this.height);
	}



	
	public void setWidth(int newWidth) {
		this.width = newWidth;
		buildBridgeMoat(this.x, this.y, this.width, this.height);
		
	}

	
	public void setX(int x) {
		this.x = x;
		buildBridgeMoat(this.x, this.y, this.width, this.height);
	}


	
	public void setY(int y) {
		this.y = y;
		buildBridgeMoat(this.x, this.y, this.width, this.height);
	}

	
	public RotatingShape getMoatLeft() {
		return this.moatLeft;
	}

	
	public RotatingShape getMoatRight() {
		return this.moatRight;
	}

	
	public RotatingShape getBridgeBottom() {
		return this.bridgeBottom;
	}

	
	public RotatingShape getBridgeTop() {
		return this.bridgeTop;
	}

	
	public RotatingShape getPlankOne() {
		return this.plankOne;
	}

	
	public RotatingShape getPlankTwo() {
		return this.plankTwo;
	}

	
	public RotatingShape getPlankThree() {
		return this.plankThree;
	}

	
	public RotatingShape getPlankFour() {
		return this.plankFour;
	}

	
	public RotatingShape getPlankFive() {
		return this.plankFive;
	}

	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		buildBridgeMoat(this.x, this.y, this.width, this.height);
		
	}

	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		buildBridgeMoat(this.x, this.y, this.width, this.height);
	}

	
	public RotatingShape getPlankSix() {
		return this.plankSix;
	}

	
	public void move(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
		buildBridgeMoat(this.x, this.y, this.width, this.height);	
	}

	
	public int getFallZoneX() {
		final int sectionSize = 3;
		final int twoThirdsOfWidth = 2*this.width/sectionSize;
		return this.x + Math.abs(twoThirdsOfWidth);
	}

	
	public int getFallZoneY() {
		final int sectionSize = 8;
		final int eightOfHeight = this.height / sectionSize;
		return this.y + Math.abs(eightOfHeight); 
	}
	
	public static void addListenerToSubShapes(BridgeMoat shape, PropertyChangeListener listener) {
		shape.getMoatLeft().addPropertyChangeListener(listener);
		shape.getMoatRight().addPropertyChangeListener(listener);
		shape.getBridgeBottom().addPropertyChangeListener(listener);
		shape.getBridgeTop().addPropertyChangeListener(listener);
		shape.getPlankOne().addPropertyChangeListener(listener);
		shape.getPlankTwo().addPropertyChangeListener(listener);
		shape.getPlankThree().addPropertyChangeListener(listener);
		shape.getPlankFour().addPropertyChangeListener(listener);
		shape.getPlankFive().addPropertyChangeListener(listener);
		shape.getPlankSix().addPropertyChangeListener(listener);
	}

	

}
