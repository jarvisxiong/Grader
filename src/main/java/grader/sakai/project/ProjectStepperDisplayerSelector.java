package grader.sakai.project;

public class ProjectStepperDisplayerSelector {
	static ProjectStepperDisplayer projectStepperDisplayer = new AnOEProjectStepperDisplayer();

	public static ProjectStepperDisplayer getProjectStepperDisplayer() {
		return projectStepperDisplayer;
	}

	public static void setProjectStepperDisplayer(
			ProjectStepperDisplayer projectStepperDisplayer) {
		ProjectStepperDisplayerSelector.projectStepperDisplayer = projectStepperDisplayer;
	}

}
