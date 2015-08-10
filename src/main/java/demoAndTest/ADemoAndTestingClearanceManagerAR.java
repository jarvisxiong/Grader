package demoAndTest;

import grader.steppers.AMainProjectStepper;

import java.awt.Color;
import java.awt.GridBagConstraints;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class ADemoAndTestingClearanceManagerAR implements ExecutableCommand {
	public Object execute(Object theFrame) {

		ObjectEditor.setAttribute(ADemoAndTestingClearanceManager.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
		ObjectEditor.setPropertyAttribute(ADemoAndTestingClearanceManager.class, "StepDescription", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 1.0);
		ObjectEditor.setPropertyAttribute(ADemoAndTestingClearanceManager.class, "StepDescription", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
		ObjectEditor.setPropertyAttribute(ADemoAndTestingClearanceManager.class, "StepDescription", AttributeNames.STRETCHABLE_BY_PARENT, true);
		ObjectEditor.setPropertyAttribute(ADemoAndTestingClearanceManager.class, "StepDescription", AttributeNames.ADD_FILL_CONSTRAINT, GridBagConstraints.BOTH);
		ObjectEditor.setPropertyAttribute(ADemoAndTestingClearanceManager.class, "StepDescription", AttributeNames.ADD_ANCHOR_CONSTRAINT, GridBagConstraints.PAGE_END);

		


		ObjectEditor.setPropertyAttribute(ADemoAndTestingClearanceManager.class, "StepDescription", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 1.0);






		return null;
	}

}
