package grader.spreadsheet;

import java.util.List;

import framework.grading.testing.TestCaseResult;
import grader.assignment.GradingFeature;

public interface FeatureGradeRecorder extends FinalGradeRecorder{
	void setGrade(String aStudentName, String anOnyen, String aFeature, double aScore);
	double getGrade(String aStudentName, String anOnyen, String aFeature);
	void setNotes(String aStudentName, String anOnyen, String aFeature, String aNotes);
	String getNotes(String aStudentName, String anOnyen, String aFeature);
	// making all feature grade recorders have the same functionality as Josh's ConglomerateRecorder
	public void newSession(final String onyen);
	public void save(double gradePercentage);
	public void save(String comments) ;
	
	// these two should be combined
	 public void setFeatureComments(String comments) ;
		public void comment(GradingFeature aGradingFeature);


	public void setFeatureResults(List<TestCaseResult> results);
	void finish();
	String getSummary();
}
