package grader.start;

import grader.sakai.project.AProjectStepper;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AGraderFilesSetterModelAR implements ExecutableCommand{
	public Object execute(Object theFrame) {

//		ObjectEditor.setPropertyAttribute(AGraderFilesSetterModel.class, "DownloadFolder", AttributeNames.LABEL_WIDTH, 90);
//		ObjectEditor.setPropertyAttribute(AGraderFilesSetterModel.class, "TextEditor", AttributeNames.LABEL_WIDTH, 90);
		ObjectEditor.setAttribute(AGraderFilesSetterModel.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
//		ObjectEditor.setAttribute(AGraderFilesSetterModel.class,  AttributeNames.STRETCH_ROWS, true);

		ObjectEditor.setPropertyAttribute(AGraderFilesSetterModel.class, "DownloadFolder", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IS_LEFT);
		ObjectEditor.setPropertyAttribute(AGraderFilesSetterModel.class, "TextEditor", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IS_LEFT);


		return null;
	}
}
