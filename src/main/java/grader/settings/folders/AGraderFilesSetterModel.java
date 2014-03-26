package grader.settings.folders;

import javax.swing.JFrame;

import util.annotations.Explanation;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AGraderFilesSetterModel implements GraderFilesSetterModel {
	FileSetterModel downloadPathModel = new AFileSetterModel();
	FileSetterModel textEditorPathModel = new AFileSetterModel();
	@Row(0)
	@Explanation("This value must be specied once per module. Other entries are derived from the problem choice. It is assumed that download folders for diferent problems of a module are in a common folder.")
	public FileSetterModel getDownloadFolder() {
		return downloadPathModel;
	}
	@Row(1)
//	@Visible(false)
	@Explanation("The OS-speificif editor for displaying source files.")
	public FileSetterModel getTextEditor() {
		return textEditorPathModel;
	}
	@Visible(false)
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
