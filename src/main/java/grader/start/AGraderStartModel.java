package grader.start;

import util.annotations.Visible;
import bus.uigen.ObjectEditor;

public class AGraderStartModel {
	GraderFilesSetterModel fileBrowsing = new AGraderFilesSetterModel();
	OnyenRangeModel onyens = new AnOnyenRangeModel();
	BeginActionModel beginActionModel = new ABeginActionModel();
	public BeginActionModel getBeginActionModel() {
		return beginActionModel;
	}
	public void setBeginActionModel(BeginActionModel beginActionModel) {
		this.beginActionModel = beginActionModel;
	}
	public GraderFilesSetterModel getFileBrowsing() {
		return fileBrowsing;
	}
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing) {
		this.fileBrowsing = fileBrowsing;
	}
	public OnyenRangeModel getOnyens() {
		return onyens;
	}
	public void setOnyens(OnyenRangeModel onyens) {
		this.onyens = onyens;
	}

	public static void main (String[] args) {
		AGraderStartModel startModel = new AGraderStartModel();
		ObjectEditor.edit(startModel);
	}
	

}
