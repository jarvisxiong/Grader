package grader.sakai;

import grader.file.FileProxy;
import grader.file.RootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;
import grader.project.Project;

import java.util.Set;

import util.misc.Common;

public class ASakaiStudentCodingAssignment extends ASakaiStudentAssignment implements StudentCodingAssignment {
    public static final String RUBRICK_SUBSTRING = "rubric";

    FileProxy rubrick;
    RootFolderProxy projectFolder;
    Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project newVal) {
        this.project = newVal;
    }

    public ASakaiStudentCodingAssignment(String aFolderName, FileProxy aFileProxy) {
        super(aFolderName, aFileProxy);
        if (isSubmitted())
            findRubrickAndProject();
    }
    
  

    void findRubrickAndProject() {
        Set<String> childrenNames = submissionFolder.getChildrenNames();
        for (String childName : childrenNames) {
            FileProxy childProxy = submissionFolder.getFileEntry(childName);
            if (childName.toLowerCase().indexOf(RUBRICK_SUBSTRING) > -1) {
                rubrick = childProxy;
            } else if (childProxy.isDirectory() ) {
            	Set<String> grandChildrenNames = childProxy.getChildrenNames();
            	if (childrenNames.size() == 0) continue;
            	String localChildName = Common.toRelativeName(submissionFolder.getLocalName(), childProxy.getLocalName()).toLowerCase();
            	for (String grandChildName:grandChildrenNames) { // look for unzipped project folder
            		 FileProxy grandChildProxy = submissionFolder.getFileEntry(grandChildName);
            		String localGrandChildName = Common.toRelativeName(childProxy.getLocalName(), grandChildProxy.getLocalName()).toLowerCase();
            		if (localGrandChildName.equals(localChildName)) {
            			projectFolder = grandChildProxy;
            			break;
            		}
            	}
            	if (projectFolder != null) 
            		continue;
            	projectFolder = childProxy;
            } else if (childProxy.getAbsoluteName().endsWith(".zip") && projectFolder == null) { // unzipped wins
                projectFolder = new AZippedRootFolderProxy(childProxy.getAbsoluteName());
            }
        }
    }

    public FileProxy getRubrick() {
        return rubrick;
    }

    public RootFolderProxy getProjectFolder() {
        return projectFolder;
    }
}
