package grader.settings.navigation;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AnAutomaticNavigationSetterAR implements ExecutableCommand{
	public Object execute(Object theFrame) {
		ObjectEditor.setAttribute(AnAutomaticNavigationSetter.class, AttributeNames.LAYOUT, AttributeNames.BORDER_LAYOUT);
//		ObjectEditor.setAttribute(AnAutomaticNavigationSetter.class, AttributeNames.STRETCHABLE_BY_PARENT, true);
//		ObjectEditor.setPropertyAttribute(AnOnyenRangeModel.class, "endingOnyen",  AttributeNames.STRETCHABLE_BY_PARENT, true);
//		ObjectEditor.setAttribute(AnOnyenRangeModel.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
//		ObjectEditor.setAttribute(AnOnyenRangeModel.class,  AttributeNames.STRETCHABLE_BY_PARENT, true);


		return null;
	}

}
