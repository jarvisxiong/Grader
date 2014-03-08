package grader.settings.folders;

import util.annotations.Row;
import bus.uigen.ObjectEditor;

public class AnOnyenRangeModel implements OnyenRangeModel{
	String startingOnyen = "", endingOnyen = "";
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
	
	public static void main (String[] args) {
		AnOnyenRangeModel onyenRangeModel = new AnOnyenRangeModel();
		ObjectEditor.edit(onyenRangeModel);
	}
	

}
