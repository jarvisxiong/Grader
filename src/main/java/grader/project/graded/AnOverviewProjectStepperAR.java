package grader.project.graded;

import util.models.AConsoleModel;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AnOverviewProjectStepperAR implements ExecutableCommand {
	
		public Object execute(Object theFrame) {
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "*", AttributeNames.VISIBLE, false);
			ObjectEditor.setMethodAttribute(AnOverviewProjectStepper.class, "*", AttributeNames.VISIBLE, false);
//			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "*", AttributeNames.LABELLED, false);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "*", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "*", AttributeNames.LABEL, "");

//			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.LABELLED, true);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.LABEL, "Overall Notes");

			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);




//			
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "ManualNotes", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "AutoNotes", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.STRETCHABLE_BY_PARENT, true);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.SCROLLED, true);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "OverallNotes", AttributeNames.ACTION_MODE, true);
//			ObjectEditor.setAttribute(AnOverviewProjectStepper.class,  AttributeNames.LAYOUT, AttributeNames.GRID_BAG_LAYOUT);
			ObjectEditor.setAttribute(AnOverviewProjectStepper.class,  AttributeNames.STRETCHABLE_BY_PARENT, true);
//			ObjectEditor.setAttribute(AnOverviewProjectStepper.class,  AttributeNames.STRETCH_COLUMNS, true);
			ObjectEditor.setAttribute(AnOverviewProjectStepper.class,  AttributeNames.STRETCH_ROWS, true);



//
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Transcript", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "GradingFeatures", AttributeNames.VISIBLE, true);
			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "GradedProjectNavigator", AttributeNames.VISIBLE, true);

			ObjectEditor.setPropertyAttribute(AnOverviewProjectStepper.class, "GradedProjectOverview", AttributeNames.VISIBLE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "AutoVisitBehavior", AttributeNames.VISIBLE, true);


//			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Photo", AttributeNames.LABELLED, false);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "ManualNotes", AttributeNames.ACTION_MODE, true);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "ManualNotes", AttributeNames.SCROLLED, true);
//
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Feedback", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Feedback", AttributeNames.SCROLLED, true);
//			
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Transcript", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
//			ObjectEditor.setPropertyAttribute(ABasicProjectStepper.class, "Transcript", AttributeNames.SCROLLED, true);
			return null;
		}

	

}
