package grader.sakai.project;

import framework.utils.GradingEnvironment;
import grader.assignment.GradingFeatureList;
import util.trace.TraceableWarning;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.trace.IllegalSourceOfPropertyNotification;
import bus.uigen.trace.UnknownPropertyNotification;

public class AnOEProjectStepperDisplayer implements ProjectStepperDisplayer<uiFrame> {
	public uiFrame display(ProjectStepper aProjectStepper) {
//		ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary",  AttributeNames.SCROLLED, true);
		
		// because of delegating property change listeners
		TraceableWarning.doNotWarn(UnknownPropertyNotification.class);
		TraceableWarning.doNotWarn(IllegalSourceOfPropertyNotification.class);

		uiFrame oeFrame = ObjectEditor.edit(aProjectStepper);
//		uiFrame oeFrame = ObjectEditor.tabEdit(aProjectStepper);

		GradingFeatureList gradingFeatures = aProjectStepper.getGradingFeatures();
		String assignmentName = aProjectStepper.getProjectDatabase().getBulkAssignmentFolder().getAssignmentName();
		String userName = GradingEnvironment.get().getUserName();
		if (userName != null && !userName.isEmpty()) {
			oeFrame.setTitle("Grading Assistant to " + userName + " for " + assignmentName);

		} else
			oeFrame.setTitle("Grading Assistant for " + assignmentName);
		oeFrame.setLocation(450, 0);
		oeFrame.setSize(880, 790);
		return oeFrame;
	}

}
