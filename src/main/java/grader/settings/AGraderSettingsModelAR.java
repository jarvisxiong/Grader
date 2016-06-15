package grader.settings;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AGraderSettingsModelAR implements ExecutableCommand {
	public Object execute(Object theFrame) {

		ObjectEditor.setAttribute(AGraderSettingsModel.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setPropertyAttribute(AGraderSettingsModel.class, "NavigationSetter", AttributeNames.VISIBLE, true);
//		ObjectEditor.setPropertyAttribute(AGraderSettingsModel.class, "NavigationSetter", AttributeNames.ROW, 2);
//		ObjectEditor.setPropertyAttribute(AGraderSettingsModel.class, "FileBrowsing", AttributeNames.ADD_FILL_CONSTRAINT, GridBagConstraints.HORIZONTAL);

		ObjectEditor.setMethodAttribute(AGraderSettingsModel.class, "Begin", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 1.0);

//		ObjectEditor.setPropertyAttribute(AGraderSettingsModel.class, "NavigationSetter", AttributeNames.CONTAINER_BACKGROUND, Color.LIGHT_GRAY);






		return null;
	}

}
