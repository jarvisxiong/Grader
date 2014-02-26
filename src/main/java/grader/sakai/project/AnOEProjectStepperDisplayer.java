package grader.sakai.project;

import java.awt.Color;

import grader.assignment.GradingFeatureList;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.oadapters.ObjectAdapter;

public class AnOEProjectStepperDisplayer implements ProjectStepperDisplayer<OEFrame> {
	public OEFrame display(ProjectStepper aProjectStepper) {
		uiFrame oeFrame = ObjectEditor.edit(aProjectStepper);
		GradingFeatureList gradingFeatures = aProjectStepper.getGradingFeatures();
		ObjectAdapter objectAdapter = oeFrame.getObjectAdapter(gradingFeatures.get(0));
		objectAdapter.setLocalAttribute(AttributeNames.COMPONENT_BACKGROUND, Color.PINK);
		objectAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, Color.PINK);

		oeFrame.refresh();
//		oeFrame.setLocation(700, 500);
		oeFrame.setLocation(0, 0);
		oeFrame.setSize(650, 800);
		return oeFrame;
	}

}
