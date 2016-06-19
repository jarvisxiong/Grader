package grader.sakai.project;

import grader.project.flexible.FlexibleProject;
import grader.sakai.StudentAssignment;

import javax.swing.Icon;

public interface SakaiProject extends FlexibleProject {
    public StudentAssignment getStudentAssignment();

    public void displaySource(SakaiProjectDatabase aSakaiProjectDatabase);

	Icon getStudentPhoto();

	void setStudentPhoto(Icon studentPhoto);

	String getProjectZipFileOrFolderMixedCaseAbsoluteName();

//	ProjectWrapper getWrapper();
//
//	void setWrapper(ProjectWrapper newValue);


}
