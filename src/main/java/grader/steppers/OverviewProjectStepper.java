package grader.steppers;

import grader.sakai.project.ProjectStepper;
import util.models.DynamicEnum;

public interface OverviewProjectStepper extends ProjectStepper, GradedProjectNavigator, GradedProjectOverview{
	DynamicEnum<String> runArgs();
	String getSource();

	void internalSetSource(String newValue);

	void setSource(String newVal);

	boolean preSetManualNotes();

	void resetFeatureSpreadsheet();
	
	public boolean preRestoreFeatureSpreadsheet();
	public void restoreFeatureSpreadsheet() ;

	void cleanAllFeedbackFolders();

	void cleanFeedbackFolder();

	String getTASourceCodeComments();

	void loadSourceFromFile();

	String getProblemHistory();

	String getStudentHistory();


	void setResultDiff(String newValue);
	String getResultDiff();

	String getSourceChecks();

}
