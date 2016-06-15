package grader.settings.navigation;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class ANavigationSetterAR implements ExecutableCommand{
	public Object execute(Object theFrame) {
//		ObjectEditor.setPropertyAttribute(ANavigationSetter.class, "navigationKind",  AttributeNames.LABEL_WIDTH, 80);
//		ObjectEditor.setPropertyAttribute(AnOnyenRangeModel.class, "endingOnyen",  AttributeNames.STRETCHABLE_BY_PARENT, true);
		ObjectEditor.setAttribute(ANavigationSetter.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setPropertyAttribute(ANavigationSetter.class, "NavigationKind",  AttributeNames.DIRECTION, AttributeNames.HORIZONTAL);
		ObjectEditor.setPropertyAttribute(ANavigationSetter.class, "NavigationKind",  AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
		ObjectEditor.setPropertyAttribute(ANavigationSetter.class, "Parameter",  AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
		ObjectEditor.setPropertyAttribute(ANavigationSetter.class, "Parameter",  AttributeNames.DIRECTION, AttributeNames.HORIZONTAL);

//		ObjectEditor.setAttribute(AnOnyenRangeModel.class,  AttributeNames.STRETCHABLE_BY_PARENT, true);


		return null;
	}

}
