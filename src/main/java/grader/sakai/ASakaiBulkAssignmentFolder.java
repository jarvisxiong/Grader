package grader.sakai;

import grader.file.FileProxy;
import grader.file.RootFolderFactory;
import grader.file.RootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;
import grader.project.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ASakaiBulkAssignmentFolder implements BulkAssignmentFolder {
    public static String DEFAULT_BULK_DOWNLOAD_FOLDER = "C:/Users/dewan/Downloads/bulk_download";
    public static String DEFAULT_GRADER_DATA_FOLDER= "-GraderData";

    public static String DEFAULT_ASSIGNMENT_NAME = "Assignment 11";
    public static String GRADES_SPREADSHEET_NAME = "grades.csv";
    boolean isAssignmentRoot;

    String bulkDownloadDirectory;
    String assignmentName;
    String mixedCaseAssignmentName;
    RootFolderProxy rootBulkDownloadFolder;
    boolean isZippedRootFolder;
    RootFolderProxy assignmentFolder;
    Set<String> studentFolderNames;
    Set<Project> studentFolders;
    FileProxy submissionFolder;
    FileProxy gradeSpreadsheet;

    public ASakaiBulkAssignmentFolder(String aBulkDownloadFolder, String anAssignmentName) {
        bulkDownloadDirectory = aBulkDownloadFolder;
        assignmentName = anAssignmentName;
        isAssignmentRoot = true;
        initializeBullkDownloadChidren();
    }

//    public ASakaiBulkAssignmentFolder(String aBulkDownloadFolder) {
//    	this(aBulkDownloadFolder, true);
////        bulkDownloadDirectory = aBulkDownloadFolder;
////        initializeAssignmentData();
////        assignmentName = assignmentFolder.getLocalName();
////        mixedCaseAssignmentName = assignmentFolder.getMixedCaseLocalName();
//    }
    public ASakaiBulkAssignmentFolder(String aBulkDownloadFolder, boolean assignmentRoot) {
    	isAssignmentRoot = assignmentRoot;
        bulkDownloadDirectory = aBulkDownloadFolder;
        initializeBullkDownloadChidren();
        assignmentName = assignmentFolder.getLocalName();
        mixedCaseAssignmentName = assignmentFolder.getMixedCaseLocalName();
    }

    void initializeBullkDownloadChidren() {
        rootBulkDownloadFolder = RootFolderFactory.createRootFolder(bulkDownloadDirectory);
        isZippedRootFolder = rootBulkDownloadFolder instanceof AZippedRootFolderProxy;
        setAssignmentFolder();
        setGradeSpreadsheet();
        setStudentFolderNames();
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getMixedCaseAssignmentName() {
        return mixedCaseAssignmentName;
    }

    RootFolderProxy extractAssignmentFolder() {
    	if (isAssignmentRoot) return rootBulkDownloadFolder;
        Set<String> childrenNames = rootBulkDownloadFolder.getChildrenNames();
        for (String childName : childrenNames) {
            FileProxy fileProxy = rootBulkDownloadFolder.getFileEntry(childName);
            if (fileProxy.isDirectory()) return fileProxy;
        }
        return rootBulkDownloadFolder.getFileEntry(rootBulkDownloadFolder.getAbsoluteName() + "/" + assignmentName);
    }

    FileProxy extractGradeSpreadsheet() {
    	String gradeSpreadsheetFullName = assignmentFolder.getAbsoluteName().replace("\\", "/") + "/" + GRADES_SPREADSHEET_NAME;
        return rootBulkDownloadFolder.getFileEntry(gradeSpreadsheetFullName);
    	
    	
    }

    public RootFolderProxy getAssignmentFolder() {
        return assignmentFolder;
    }

    void setAssignmentFolder() {
        assignmentFolder = extractAssignmentFolder();
    }
    
    public static boolean hasOnyenSyntax(String fileName) {
    	return fileName.length() > 0 &&  Character.isLetter(fileName.charAt(0)) && fileName.matches("\\w.*, .*\\(.*\\).*");
    }
    
    public static String extractOnyen(String fileName) {
    	return fileName.replaceAll(".*\\((.*)\\).*", "$1");
    }

    Set<String> extractStudentFolderNames() {
        Set<String> retVal = new HashSet(
                assignmentFolder.getChildrenNames());
        List<String> fileChildren = new ArrayList();
        for (String childName : retVal) {
            FileProxy fileProxy = rootBulkDownloadFolder.getFileEntry(childName);
            
            if (!fileProxy.isDirectory() || !hasOnyenSyntax(fileProxy.getLocalName())) { // can add grader data or other info to downloaded folder
                fileChildren.add(childName);
            }
        }
        for (String fileChild : fileChildren) {
            retVal.remove(fileChild);
        }
        return retVal;
    }

    void setStudentFolderNames() {
        studentFolderNames = extractStudentFolderNames();
    }

    void setGradeSpreadsheet() {
        gradeSpreadsheet = extractGradeSpreadsheet();
    }

    @Override
    public FileProxy getSpreadsheet() {
        return gradeSpreadsheet;
    }

    @Override
    public Set<String> getStudentFolderNames() {
        return studentFolderNames;
    }

    @Override
    public FileProxy getStudentFolder(String aName) {
        return rootBulkDownloadFolder.getFileEntry(aName);
    }

}
