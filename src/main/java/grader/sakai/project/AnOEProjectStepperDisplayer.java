package grader.sakai.project;

import java.awt.Color;

import grader.assignment.AGradingFeature;
import grader.assignment.GradingFeatureList;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.oadapters.ObjectAdapter;

public class AnOEProjectStepperDisplayer implements ProjectStepperDisplayer<uiFrame> {
	public uiFrame display(ProjectStepper aProjectStepper) {
		ObjectEditor.setPropertyAttribute(AProjectStepper.class, "summary",  AttributeNames.SCROLLED, true);

		uiFrame oeFrame = ObjectEditor.edit(aProjectStepper);
		GradingFeatureList gradingFeatures = aProjectStepper.getGradingFeatures();
		oeFrame.setLocation(0, 0);
		oeFrame.setSize(830, 800);
		return oeFrame;
	}

}
