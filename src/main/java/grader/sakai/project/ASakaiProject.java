package grader.sakai.project;

import grader.file.FileProxy;
import grader.project.AProject;
import grader.sakai.StudentAssignment;
import grader.sakai.StudentCodingAssignment;

import java.io.File;
import java.util.List;

import javax.swing.Icon;

public class ASakaiProject extends AProject implements SakaiProject {
    StudentCodingAssignment studentAssignment;
    Icon studentPhoto;
    

	public ASakaiProject(StudentCodingAssignment aStudentCodingAssignment, String aSourceSuffix, String anOutputSuffix) {
        super(aStudentCodingAssignment, aSourceSuffix, anOutputSuffix);
        studentAssignment = aStudentCodingAssignment;
        List<String> documents = studentAssignment.getDocuments();
        documents.remove(getOutputFileName());
        documents.remove(getSourceFileName());
    }

    public StudentAssignment getStudentAssignment() {
        return studentAssignment;
    }

    public void displaySource(SakaiProjectDatabase aSakaiProjectDatabase) {
        maybeMakeClassDescriptions();
        aSakaiProjectDatabase.getSourceDisplayer().displaySource(this);
    }
    @Override
    public Icon getStudentPhoto() {
		return studentPhoto;
	}
    @Override
	public void setStudentPhoto(Icon studentPhoto) {
		this.studentPhoto = studentPhoto;
	}
     @Override
    public String getProjectZipFileOrFolderMixedCaseAbsoluteName() {
    	FileProxy zipFile = studentAssignment.getZipFile();
    	if (zipFile != null) {
    		return zipFile.getMixedCaseAbsoluteName();
    	} else {
    		return getRootCodeFolder().getMixedCaseAbsoluteName();
    	}
    }
}
