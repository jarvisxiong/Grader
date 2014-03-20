package grader.project.graded;

import util.annotations.Visible;
import grader.sakai.project.ProjectStepper;

public interface OverviewProjectStepper extends ProjectStepper, GradedProjectNavigator, GradedProjectOverview{

	String getSource();

	void internalSetSource(String newValue);

	void setSource(String newVal);

	boolean preSetManualNotes();

	void resetFeatureSpreadsheet();
	
	public boolean preRestoreFeatureSpreadsheet();
	public void restoreFeatureSpreadsheet() ;

}
