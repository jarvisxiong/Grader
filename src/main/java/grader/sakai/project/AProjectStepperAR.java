package grader.sakai.project;

import util.pipe.AConsoleModel;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AProjectStepperAR implements ExecutableCommand {
	
		public Object execute(Object theFrame) {
//			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary", AttributeNames.LABELLED, false);
			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "Photo", AttributeNames.LABELLED, false);
			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "ManualNotes", AttributeNames.ACTION_MODE, true);
			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "ManualNotes", AttributeNames.SCROLLED, true);

			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "Feedback", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "Feedback", AttributeNames.SCROLLED, true);
			
			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "Transcript", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
			ObjectEditor.setPropertyAttribute(AProjectStepper.class, "Transcript", AttributeNames.SCROLLED, true);
			return null;
		}

	

}
