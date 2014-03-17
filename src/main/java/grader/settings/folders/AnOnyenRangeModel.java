package grader.settings.folders;

import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnOnyenRangeModel implements OnyenRangeModel{
	String startingOnyen = "", endingOnyen = "", goToOnyen = "";
    @Row(0)
	public String getStartingOnyen() {
		return startingOnyen;
	}

	public void setStartingOnyen(String startingOnyen) {
		this.startingOnyen = startingOnyen;
	}
	@Row(1)
	public String getEndingOnyen() {
		return endingOnyen;
	}

	public void setEndingOnyen(String endingOnyen) {
		this.endingOnyen = endingOnyen;
	}
	@Row(2)
	@Override
	public String getGoToOnyen() {
		return goToOnyen;
	}
    @Override
	public void setGoToOnyen(String goToOnyen) {
		this.goToOnyen = goToOnyen;
	}

	public static void main (String[] args) {
		AnOnyenRangeModel onyenRangeModel = new AnOnyenRangeModel();
		ObjectEditor.edit(onyenRangeModel);
	}
	

}
