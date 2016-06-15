package grader.settings.navigation;

import grader.navigation.filter.AGradingStatusFilter;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class ANavigationFilterSetterAR implements ExecutableCommand{
	public Object execute(Object theFrame) {
//		ObjectEditor.setPropertyAttribute(ANavigationSetter.class, "navigationKind",  AttributeNames.LABEL_WIDTH, 80);
//		ObjectEditor.setPropertyAttribute(AnOnyenRangeModel.class, "endingOnyen",  AttributeNames.STRETCHABLE_BY_PARENT, true);
		ObjectEditor.setAttribute(ANavigationFilterSetter.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setPropertyAttribute(ANavigationFilterSetter.class, "Parameter",  AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
		ObjectEditor.setPropertyAttribute(ANavigationFilterSetter.class, "Parameter",  AttributeNames.DIRECTION, AttributeNames.HORIZONTAL);
		ObjectEditor.setPropertyAttribute(ANavigationFilterSetter.class, "Parameter",  AttributeNames.EXPLANATION, 
				ANavigationFilterSetter.getExplanation(AGradingStatusFilter.class));

//		ObjectEditor.setAttribute(AnOnyenRangeModel.class,  AttributeNames.STRETCHABLE_BY_PARENT, true);


		return null;
	}

}
