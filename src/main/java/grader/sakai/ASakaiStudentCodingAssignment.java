package grader.sakai;

import grader.file.FileProxy;
import grader.file.RootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;
import grader.project.Project;
import grader.trace.project.ProjectFolderNotFound;
import grader.trace.project.RubrickFileLoaded;

import java.util.Set;

import util.misc.Common;
import util.trace.Tracer;

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
    
    FileProxy getZipChild(FileProxy aFolder) {
    	Set<String> childrenNames = aFolder.getChildrenNames();
    	for (String childName:childrenNames) {
    		if (childName.endsWith(".zip")) return submissionFolder.getFileEntry(childName);
    	}
    	return null;
  
    }
    
    FileProxy getUniqueNonMACOSFolderChild(FileProxy aFolder) {
    	Set<String> childrenNames = aFolder.getChildrenNames();
    	FileProxy folderChild = null;
    	for (String childName:childrenNames) {
    		FileProxy child = submissionFolder.getFileEntry(childName);
    		if (child.isDirectory() && childName.indexOf("MACOSX") == -1 ) {
    			if (folderChild != null)
    				return null;
    			else
    				folderChild = child;
    		}    		
    	}
    	return folderChild;

    }
    
    FileProxy getUnzippedFolder(FileProxy aFolder, FileProxy zipFile) {
    	String name = zipFile.getParentRelativeName();
    	String normalizedName = name.substring(0, name.indexOf(".")).toLowerCase();
    	Set<String> childrenNames = aFolder.getChildrenNames();
    	FileProxy folderChild = null;
    	for (String childName:childrenNames) {
    		FileProxy child = submissionFolder.getFileEntry(childName);
    		if (child == zipFile) continue;
    		if (child.getParentRelativeName().toLowerCase().equals(normalizedName))	
    			return child;
    	} 	
    	return null;
    	
    	
    }
    
    FileProxy findRubrick(FileProxy aFolder) {
    	Set<String> childrenNames = aFolder.getChildrenNames();
//    	FileProxy retVal;
        for (String childName : childrenNames) {
            FileProxy childProxy = submissionFolder.getFileEntry(childName);
            if (childName.toLowerCase().indexOf(RUBRICK_SUBSTRING) > -1) {
            	RubrickFileLoaded.newCase(childName, this);
                return childProxy;
            }
        }
        return null;
    }

    void findRubrickAndProject() {
    	rubrick = findRubrick(submissionFolder);
    	FileProxy zipFile = getZipChild(submissionFolder);
    	if (zipFile == null) {
    		projectFolder = getUniqueNonMACOSFolderChild(submissionFolder);
    	} else {
    		FileProxy unzippedFolder = getUnzippedFolder(submissionFolder, zipFile);
    		if (unzippedFolder == null) {
    			projectFolder = new AZippedRootFolderProxy(zipFile.getAbsoluteName());
    		} else {
    			projectFolder = getUniqueNonMACOSFolderChild(unzippedFolder);
    			if (projectFolder == null)
    				projectFolder = unzippedFolder; // not sure if this is ever reasonable
    		}	
    	
    	}
    	if (projectFolder == null) {
    		ProjectFolderNotFound.newCase(submissionFolder.getAbsoluteName(), this);
//    		Tracer.error("No project folder found in " + submissionFolder.getAbsoluteName());
    	}
        
    }
//    void findRubrickAndProjectOld() {
//        Set<String> childrenNames = submissionFolder.getChildrenNames();
//        for (String childName : childrenNames) {
//            FileProxy childProxy = submissionFolder.getFileEntry(childName);
//            if (childName.toLowerCase().indexOf(RUBRICK_SUBSTRING) > -1) {
//                rubrick = childProxy;
//            } else if (childProxy.isDirectory() ) {
//            	Set<String> grandChildrenNames = childProxy.getChildrenNames();
//            	if (childrenNames.size() == 0) continue;
//            	String localChildName = Common.toRelativeName(submissionFolder.getLocalName(), childProxy.getLocalName()).toLowerCase();
//            	for (String grandChildName:grandChildrenNames) { // look for unzipped project folder
//            		 FileProxy grandChildProxy = submissionFolder.getFileEntry(grandChildName);
//            		String localGrandChildName = Common.toRelativeName(childProxy.getLocalName(), grandChildProxy.getLocalName()).toLowerCase();
//            		if (localGrandChildName.equals(localChildName)) {
//            			projectFolder = grandChildProxy;
//            			break;
//            		}
//            	}
//            	if (projectFolder != null) 
//            		continue;
//            	projectFolder = childProxy;
//            } else if (childProxy.getAbsoluteName().endsWith(".zip") && projectFolder == null) { // unzipped wins
//                projectFolder = new AZippedRootFolderProxy(childProxy.getAbsoluteName());
//            }
//        }
//    }

    public FileProxy getRubrick() {
        return rubrick;
    }

    public RootFolderProxy getProjectFolder() {
        return projectFolder;
    }
}
