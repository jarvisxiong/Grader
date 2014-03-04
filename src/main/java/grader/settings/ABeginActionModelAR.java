package grader.settings;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class ABeginActionModelAR implements ExecutableCommand{
	public Object execute(Object theFrame) {

//		ObjectEditor.setPropertyAttribute(AGraderFilesSetterModel.class, "DownloadFolder", AttributeNames.LABEL_WIDTH, 90);
//		ObjectEditor.setPropertyAttribute(AGraderFilesSetterModel.class, "TextEditor", AttributeNames.LABEL_WIDTH, 90);

		ObjectEditor.setAttribute(ABeginActionModel.class,  AttributeNames.ELIDE_IF_NO_COMPONENTS, false);


		return null;
	}

}
