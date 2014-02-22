package grader.spreadsheet;

public interface FeatureGradeRecorder extends FinalGradeRecorder{
	void setGrade(String aStudentName, String anOnyen, String aFeature, double aScore);
	double getGrade(String aStudentName, String anOnyen, String aFeature);
	void setNotes(String aStudentName, String anOnyen, String aFeature, String aNotes);
	String getNotes(String aStudentName, String anOnyen, String aFeature);

}
