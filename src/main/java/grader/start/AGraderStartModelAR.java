package grader.start;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AGraderStartModelAR implements ExecutableCommand {
	public Object execute(Object theFrame) {

		ObjectEditor.setAttribute(AGraderStartModel.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);



		return null;
	}

}
