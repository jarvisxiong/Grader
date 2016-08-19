	package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Location", "HorizontalLines", "VerticalLines"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public class ABridge extends AnAdvLocatable implements Bridge {
	
	private RotatingLine[] horizontalLines, verticalLines;
	private RotatingLine horizontalLine, verticalLine;
	private AdvLocatable tempLocation;
	final double verticalAngle, vLength = (double) 150, hLength;
	final int numHLines = 2, plankLength = 20;
	int numVLines;
	
	public ABridge(AdvLocatable initLocation, double initLength, double initAngle){
		super.setLocation(initLocation);
		hLength = initLength;
		numVLines = (int) (hLength/plankLength);
		verticalAngle = initAngle;
		horizontalLine = new ARotatingLine(getLocation(), hLength, (double) 0);
		verticalLine = new ARotatingLine(getLocation(), vLength, verticalAngle);
		verticalLines = new RotatingLine[numVLines + 1];
		setHorizontalLines();
		setVerticalLines();
	}

	public RotatingLine[] getHorizontalLines() {
		return horizontalLines;
	}
	public void setHorizontalLines() {
		horizontalLines = new RotatingLine[numHLines];
		horizontalLines[0] = horizontalLine;
		horizontalLines[1] = new ARotatingLine(getLocation().getRelativeLocation(vLength, verticalAngle), hLength, (double) 0);
	}

	public RotatingLine[] getVerticalLines() {
		return verticalLines;
	}
	public void setVerticalLines(){
		tempLocation = getLocation();
		verticalLines[0] = verticalLine;
		for (int i = 1; i < numVLines; i++){
			tempLocation = tempLocation.getRelativeLocation(plankLength,0);
			verticalLines[i] = new ARotatingLine(tempLocation, vLength, verticalAngle);
		}
		verticalLines[numVLines] = new ARotatingLine(getLocation().getRelativeLocation(hLength, 0), vLength, verticalAngle);
	}
	@Override
	public void setLocation(AdvLocatable newLocation) {
		super.setLocation(newLocation);
		tempLocation = getLocation();
		verticalLines[0].setLocation(getLocation());
		for (int i = 1; i < numVLines; i++){
			tempLocation = tempLocation.getRelativeLocation(plankLength,0);
			verticalLines[i].setLocation(tempLocation);
		}
		horizontalLines[0].setLocation(getLocation());
		horizontalLines[1].setLocation(getLocation().getRelativeLocation(vLength, verticalAngle));
		verticalLines[numVLines].setLocation(horizontalLines[1].getLocation());
	}
	
}
