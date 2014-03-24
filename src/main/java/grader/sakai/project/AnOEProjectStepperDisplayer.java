package grader.sakai.project;

import java.awt.Color;

import util.trace.TraceableWarning;
import framework.utils.GradingEnvironment;
import grader.assignment.AGradingFeature;
import grader.assignment.GradingFeatureList;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.oadapters.ObjectAdapter;
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
		oeFrame.setLocation(0, 0);
		oeFrame.setSize(850, 790);
		return oeFrame;
	}

}
