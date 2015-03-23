package grader.project.graded;

import java.awt.GridBagConstraints;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import scala.collection.convert.Wrappers.SetWrapper;
import util.pipe.AConsoleModel;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AComplexProjectStepperAR implements ExecutableCommand {
	
		public Object execute(Object theFrame) {
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "*", AttributeNames.VISIBLE, false);
//			ObjectEditor.setMethodAttribute(AMainProjectStepper.class, "*", AttributeNames.VISIBLE, false);
			ObjectEditor.setAttribute(AComplexProjectStepper.class, AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
			ObjectEditor.setAttribute(AComplexProjectStepper.class,  AttributeNames.LABEL, "Grading Assistant");

			ObjectEditor.setAttribute(AComplexProjectStepper.class, AttributeNames.STRETCH_ROWS, true);
			ObjectEditor.setPreferredWidget(AComplexProjectStepper.class, JTabbedPane.class);
			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Source", AttributeNames.SCROLLED, true);
			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Source", AttributeNames.ACTION_MODE, true);

//			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "NavigationSetter", AttributeNames.ADD_FILL_CONSTRAINT, GridBagConstraints.BOTH);	
//			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "NavigationSetter", AttributeNames.ADD_WEIGHT_Y_CONSTRAINT, 1.0);			



//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "AutoNotes", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverallNotes", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.VISIBLE, true);

//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "GradingFeatures", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.LABELLED, false);

//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "OverviewProjectStepper", AttributeNames.VISIBLE, true);

//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "GradedProjectOverview", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "AutoVisitBehavior", AttributeNames.VISIBLE, true);


//			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Photo", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.ACTION_MODE, true);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "ManualNotes", AttributeNames.SCROLLED, true);
//
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Feedback", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Feedback", AttributeNames.SCROLLED, true);
//			
//			ObjectEditor.setPropertyAttribute(AMainProjectStepper.class, "Transcript", AttributeNames.PREFERRED_WIDGET, true);
			
//			ObjectEditor.setPreferredWidget(AComplexProjectStepper.class, "Transcript", JTextArea.class);
////			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Transcript", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Transcript", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
//			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Transcript", AttributeNames.SCROLLED, true);			
//			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Transcript", AttributeNames.COMPONENT_WIDTH, 600);
//			ObjectEditor.setPropertyAttribute(AComplexProjectStepper.class, "Transcript", AttributeNames.COMPONENT_HEIGHT, 400);

			return null;
		}

	

}
