package grader.start;

import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AGraderFilesSetterModel implements GraderFilesSetterModel {
	FileSetterModel downloadPathModel = new AFileSetterModel();
	FileSetterModel textEditorPathModel = new AFileSetterModel();
	
	public FileSetterModel getDownloadFolder() {
		return downloadPathModel;
	}
	public FileSetterModel getTextEditor() {
		return textEditorPathModel;
	}
	
	public void initFrame(JFrame aFrame) {
		downloadPathModel.initFrame(aFrame);
		textEditorPathModel.initFrame(aFrame);
	}
	
	public static void main (String[] args) {
		AGraderFilesSetterModel fileSettersModel = new AGraderFilesSetterModel();
		OEFrame oeFrame = ObjectEditor.edit(fileSettersModel);
		fileSettersModel.initFrame((JFrame) oeFrame.getFrame().getPhysicalComponent());
		oeFrame.setSize(600, 300);
	}

	

}
