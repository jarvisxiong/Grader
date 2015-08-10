package grader.steppers;

import grader.settings.AGraderSettingsModel;

import java.awt.GridBagConstraints;

import javax.swing.JTextArea;

import scala.collection.convert.Wrappers.SetWrapper;
import util.pipe.AConsoleModel;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AMainProjectStepperAR implements ExecutableCommand {
	
		public Object execute(Object theFrame) {
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "*", AttributeNames.VISIBLE, false);
//			ObjectEditor.setMethodAttribute(AMainProjectStepper.class, "*", AttributeNames.VISIBLE, false);
			ObjectEditor.setAttribute(AMainProjectStepper.class, AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
			
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "AutoNotes", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.SCROLLED, true);			

//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "GradingFeatures", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.ADD_ANCHOR_CONSTRAINT, GridBagConstraints.PAGE_START);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 0.0);

//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.VISIBLE, true);

//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "GradedProjectOverview", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "AutoVisitBehavior", AttributeNames.VISIBLE, true);


//			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Photo", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.ACTION_MODE, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.SCROLLED, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "AutoNotes", AttributeNames.SCROLLED, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.ACTION_MODE, true);


//
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Feedback", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Feedback", AttributeNames.SCROLLED, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.STRETCHABLE_BY_PARENT, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.SCROLLED, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.ACTION_MODE, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);

//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.PREFERRED_WIDGET, true);
			
			ObjectEditor.setPreferredWidget(AMainProjectStepper.class, "Transcript", JTextArea.class);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.SCROLLED, true);			
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.COMPONENT_WIDTH, 650);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.COMPONENT_HEIGHT, 240);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.ADD_ANCHOR_CONSTRAINT, GridBagConstraints.PAGE_END);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 1.0);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.ADD_FILL_CONSTRAINT, GridBagConstraints.BOTH);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.STRETCHABLE_BY_PARENT, true);
			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 1.0);


			return null;
		}

	

}
