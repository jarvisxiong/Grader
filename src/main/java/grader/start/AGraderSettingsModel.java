package grader.start;

import util.annotations.ComponentHeight;
import util.annotations.Row;
import util.annotations.Visible;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AGraderSettingsModel {
	GraderFilesSetterModel fileBrowsing = new AGraderFilesSetterModel();
	OnyenRangeModel onyens = new AnOnyenRangeModel();
	BeginActionModel beginActionModel = new ABeginActionModel();
	
	@Row(0)
	public GraderFilesSetterModel getFileBrowsing() {
		return fileBrowsing;
	}
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing) {
		this.fileBrowsing = fileBrowsing;
	}
	@Row(1)
	public OnyenRangeModel getOnyens() {
		return onyens;
	}
	public void setOnyens(OnyenRangeModel onyens) {
		this.onyens = onyens;
	}
	@Row(2)
	@ComponentHeight(25)
	public void begin() {
		
	}
//	public BeginActionModel getBeginActionModel() {
//		return beginActionModel;
//	}
//	public void setBeginActionModel(BeginActionModel beginActionModel) {
//		this.beginActionModel = beginActionModel;
//	}

	public static void main (String[] args) {
		AGraderSettingsModel startModel = new AGraderSettingsModel();
		OEFrame frame = ObjectEditor.edit(startModel);
		frame.setTitle("Grader Settings");
		frame.setSize(500, 250);
	}
	

}
