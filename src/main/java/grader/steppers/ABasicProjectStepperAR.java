package grader.steppers;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class ABasicProjectStepperAR implements ExecutableCommand {
	
		public Object execute(Object theFrame) {
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "*", AttributeNames.VISIBLE, false);
			ObjectEditor.setMethodAttribute(ABasicProjectStepper.class, "*", AttributeNames.VISIBLE, false);

			
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "ManualNotes", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "AutoNotes", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "OverallNotes", AttributeNames.VISIBLE, true);

			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Transcript", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "GradingFeatures", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "GradedProjectNavigator", AttributeNames.VISIBLE, true);

			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "GradedProjectOverview", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "AutoVisitBehavior", AttributeNames.VISIBLE, true);


//			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Photo", AttributeNames.LABELLED, false);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "ManualNotes", AttributeNames.ACTION_MODE, true);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "ManualNotes", AttributeNames.SCROLLED, true);

			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Feedback", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Feedback", AttributeNames.SCROLLED, true);
			
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Transcript", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Transcript", AttributeNames.SCROLLED, true);
			return null;
		}

	

}
