package grader.project.graded;

import grader.sakai.project.ProjectStepper;

public interface OverviewProjectStepper extends ProjectStepper, GradedProjectNavigator, GradedProjectOverview{

	String getSource();

	void internalSetSource(String newValue);

	void setSource(String newVal);

}
