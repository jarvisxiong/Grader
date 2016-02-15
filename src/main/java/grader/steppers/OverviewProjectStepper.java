package grader.steppers;

import java.util.List;

import bus.uigen.models.PropertyFocusListener;
import util.annotations.Visible;
import util.models.DynamicEnum;
import grader.sakai.project.ProjectStepper;

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
