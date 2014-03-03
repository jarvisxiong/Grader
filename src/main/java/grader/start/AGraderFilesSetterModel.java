package grader.start;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AGraderFilesSetterModel {
	FileSetterModel downloadPathModel = new AFileSetterModel();
	FileSetterModel textEditorPathModel = new AFileSetterModel();
	
	public FileSetterModel getDownloadFolder() {
		return downloadPathModel;
	}
	public FileSetterModel getTextEditor() {
		return textEditorPathModel;
	}
	
	public void initFrame(OEFrame aFrame) {
		downloadPathModel.initFrame(aFrame);
		textEditorPathModel.initFrame(aFrame);
	}
	
	public static void main (String[] args) {
		AGraderFilesSetterModel fileSettersModel = new AGraderFilesSetterModel();
		OEFrame oeFrame = ObjectEditor.edit(fileSettersModel);
		fileSettersModel.initFrame(oeFrame);
		oeFrame.setSize(600, 300);
	}

	

}
