package grader.modules;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AModuleProblemSelectorAR implements ExecutableCommand{
	public Object execute(Object theFrame) {
//		ObjectEditor.setAttribute(AModuleProblemSelector.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setAttribute(AModuleProblemSelector.class,  AttributeNames.DIRECTION, AttributeNames.HORIZONTAL);

//		ObjectEditor.setAttribute(AFileSetterModel.class,  AttributeNames.STRETCHABLE_BY_PARENT, true);


		return null;
	}

}
