package grader.steppers;

public interface MainProjectStepper extends OverviewProjectStepper{

	OverviewProjectStepper getOverviewProjectStepper();

	void setOverviewProjectStepper(OverviewProjectStepper overviewProjectStepper);

}
