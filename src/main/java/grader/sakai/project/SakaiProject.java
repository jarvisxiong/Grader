package grader.sakai.project;

import javax.swing.Icon;

import grader.project.Project;
import grader.sakai.StudentAssignment;

public interface SakaiProject extends Project {
    public StudentAssignment getStudentAssignment();

    public void displaySource(SakaiProjectDatabase aSakaiProjectDatabase);

	Icon getStudentPhoto();

	void setStudentPhoto(Icon studentPhoto);


}
