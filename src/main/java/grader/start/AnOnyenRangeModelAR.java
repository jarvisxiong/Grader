package grader.start;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AnOnyenRangeModelAR implements ExecutableCommand{
	public Object execute(Object theFrame) {
		ObjectEditor.setPropertyAttribute(AnOnyenRangeModel.class, "startingOnyen",  AttributeNames.STRETCHABLE_BY_PARENT, true);
		ObjectEditor.setPropertyAttribute(AnOnyenRangeModel.class, "endingOnyen",  AttributeNames.STRETCHABLE_BY_PARENT, true);
		ObjectEditor.setAttribute(AnOnyenRangeModel.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setAttribute(AnOnyenRangeModel.class,  AttributeNames.STRETCHABLE_BY_PARENT, true);


		return null;
	}

}
