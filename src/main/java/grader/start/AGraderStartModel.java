package grader.start;

import util.annotations.Row;
import util.annotations.Visible;
import bus.uigen.ObjectEditor;

public class AGraderStartModel {
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
	public BeginActionModel getBeginActionModel() {
		return beginActionModel;
	}
	public void setBeginActionModel(BeginActionModel beginActionModel) {
		this.beginActionModel = beginActionModel;
	}

	public static void main (String[] args) {
		AGraderStartModel startModel = new AGraderStartModel();
		ObjectEditor.edit(startModel);
	}
	

}
