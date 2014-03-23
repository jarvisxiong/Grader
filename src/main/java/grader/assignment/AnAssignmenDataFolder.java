package grader.assignment;

import grader.file.FileProxy;
import grader.file.FileProxyUtils;
import grader.file.filesystem.AFileSystemFileProxy;
import grader.file.filesystem.AFileSystemRootFolderProxy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class AnAssignmenDataFolder extends AFileSystemRootFolderProxy implements AssignmentDataFolder {

    public static final String ID_FILE_NAME = "onyens.txt";
    public static final String DEFAULT_LOG_FILE_NAME = "log.txt";
    public static final String DEFAULT_SKIPPED_FILE_NAME = "skipped_onyens.txt";
    public static final String DEFAULT_GRADED_ID_FILE_NAME = "graded_onyens.txt";

    public static final String DEFAULT_INPUT_FOLDER_NAME = "input";
    public static final String DEFAULT_FEATURE_GRADE_FILE_NAME = "FeatureGrades.csv";
    public static final String DEFAULT_BACKUP_FEATURE_GRADE_FILE_NAME = "BackupFeatureGrades.csv";
    String idFileName = ID_FILE_NAME;
    String gradedIdFileName;
    String skippedIdFileName;
    String logFileName;
    String inputFolderName = DEFAULT_INPUT_FOLDER_NAME;
    String featureGradeFileName = DEFAULT_FEATURE_GRADE_FILE_NAME;
    String backupFeatureGradeFileName = DEFAULT_BACKUP_FEATURE_GRADE_FILE_NAME;
    
    File originalFeatureGradeFile, backupFeatureGradeFile;
    

    String idText;
    Set<String> inputFiles;
    List<String> studentIDs;
    FileProxy finalGradeFile, featureGradeFile;


    public AnAssignmenDataFolder(String aRootFolderName, FileProxy aFinalGradeFile) {
        super(aRootFolderName);
        finalGradeFile = aFinalGradeFile;
        if (rootFolder != null)
            initGraderData();
    }
    
  

    void initGraderData() {

        FileProxy inputFolder = this.getFileEntryFromLocalName(inputFolderName);
        if (inputFolder != null)
            inputFiles = inputFolder.getChildrenNames();
        else
        	inputFiles = new HashSet<>();
        FileProxy idFileProxy = getFileEntryFromLocalName(idFileName);
        featureGradeFile = getFileEntryFromLocalName(featureGradeFileName);
        gradedIdFileName = rootFolder.getAbsolutePath() + "/" + DEFAULT_GRADED_ID_FILE_NAME;
        skippedIdFileName = rootFolder.getAbsolutePath() + "/" + DEFAULT_SKIPPED_FILE_NAME;
        logFileName = rootFolder.getAbsolutePath() + "/" + DEFAULT_LOG_FILE_NAME;
        initFeatureGradeFiles();
//        if (finalGradeFile != null && (featureGradeFile == null || !featureGradeFile.exists())) {
//            String fullFeatureGradeFileName = rootFolder.getAbsolutePath() + "/" + featureGradeFileName;
//            File featureFile = new File(fullFeatureGradeFileName);
//            File aFinalGradeFile = new File(finalGradeFile.getAbsoluteName());
//
//            try {
//                Files.copy(aFinalGradeFile.toPath(), featureFile.toPath(), REPLACE_EXISTING);
//                featureGradeFile = new AFileSystemFileProxy(this, new File(fullFeatureGradeFileName), this.getAbsoluteName());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
        studentIDs = FileProxyUtils.toList(idFileProxy);

    }
     void initFeatureGradeFiles() {
         String fullFeatureGradeFileName = rootFolder.getAbsolutePath() + "/" + featureGradeFileName;
         backupFeatureGradeFile = new File(rootFolder.getAbsolutePath() + "/" + backupFeatureGradeFileName);
         originalFeatureGradeFile = new File(fullFeatureGradeFileName);

    	if (finalGradeFile != null && (featureGradeFile == null || !featureGradeFile.exists())) {
//            String fullFeatureGradeFileName = rootFolder.getAbsolutePath() + "/" + featureGradeFileName;
//             originalFeatureGradeFile = new File(fullFeatureGradeFileName);
             
            File aFinalGradeFile = new File(finalGradeFile.getAbsoluteName());

            try {
                Files.copy(aFinalGradeFile.toPath(), originalFeatureGradeFile.toPath(), REPLACE_EXISTING);
                featureGradeFile = new AFileSystemFileProxy(this, new File(fullFeatureGradeFileName), this.getAbsoluteName());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        backupFeatureGradeFile = new File(rootFolder.getAbsolutePath() + "/" + backupFeatureGradeFileName);
//        originalFeatureGradeFile = new File(fullFeatureGradeFileName);

    	
    }

    public Set<String> getInputFiles() {
        return inputFiles;
    }

    public List<String> getStudentIDs() {
        return studentIDs;
    }

    @Override
    public FileProxy getFeatureGradeFile() {
        return featureGradeFile;
    }

    public String getIdFileName() {
        return idFileName;
    }

//    public void setIdFileName(String idFileName) {
//        this.idFileName = idFileName;
//    }

    public String getGradedIdFileName() {
        return gradedIdFileName;
    }

    public void setGradedIdFileName(String gradedIdFileName) {
        this.gradedIdFileName = gradedIdFileName;
    }

    public String getSkippedIdFileName() {
        return skippedIdFileName;
    }

    public void setSkippedIdFileName(String skippedIdFileName) {
        this.skippedIdFileName = skippedIdFileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public String getInputFolderName() {
        return inputFolderName;
    }

    public void setInputFolderName(String inputFolderName) {
        this.inputFolderName = inputFolderName;
    }

    public String getFeatureGradeFileName() {
        return featureGradeFileName;
    }

    public void setFeatureGradeFileName(String featureGradeFileName) {
        this.featureGradeFileName = featureGradeFileName;
    }

    public String getIdText() {
        return idText;
    }

    public void setIdText(String idText) {
        this.idText = idText;
    }

    public FileProxy getFinalGradeFile() {
        return finalGradeFile;
    }

    public void setFinalGradeFile(FileProxy finalGradeFile) {
        this.finalGradeFile = finalGradeFile;
    }

    public void setInputFiles(Set<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public void setStudentIDs(List<String> studentIDs) {
        this.studentIDs = studentIDs;
    }

    public void setFeatureGradeFile(FileProxy featureGradeFile) {
        this.featureGradeFile = featureGradeFile;
    }


    @Override
	public String getBackupFeatureGradeFileName() {
		return backupFeatureGradeFileName;
	}


    @Override
	public void setBackupFeatureGradeFileName(String backupFeatureGradeFileName) {
		this.backupFeatureGradeFileName = backupFeatureGradeFileName;
	}
    // throw exception instead?
    public boolean removeBackupFeatureGradeFile() {
//    	String fullBackupName = getAbsoluteName() + "/" + backupFeatureGradeFileName;
//    	File backup = new File(fullBackupName);
    	if (!backupFeatureGradeFile.exists()) return true;    	
    	return backupFeatureGradeFile.delete();
    }
    @Override
    public boolean removeFeatureGradeFile() {
    	boolean retVal = removeBackupFeatureGradeFile();
    	if (!retVal) return false;
//    	String fullFeatureName = getAbsoluteName() + "/" + featureGradeFileName;
//    	File original = new File (fullFeatureName);
//    	String fullBackupName = getAbsoluteName() + "/" + backupFeatureGradeFileName;
//    	File backup = new File(fullBackupName);    	
    	originalFeatureGradeFile.renameTo(backupFeatureGradeFile);
//    	initFeatureGradeFiles();
    	return true;   	
    	
    }
    @Override
    public boolean restoreFeatureGradeFile() {
    	if (!backupExists())
    		return false;
    	boolean retVal;
    	retVal = originalFeatureGradeFile.delete();
    	if (!retVal)
    		return false;
    	 retVal =  backupFeatureGradeFile.renameTo(originalFeatureGradeFile);
    	if (!retVal) 
    		return false;
    	initFeatureGradeFiles();
    	return true;
    	
    	
    }
    @Override
    public boolean backupExists() {
//    	String fullBackupName = getAbsoluteName() + "/" + backupFeatureGradeFileName;
//    	File backup = new File(fullBackupName);
    	return backupFeatureGradeFile.exists();
    	
    }
//    public boolean restoreFeatureGradeFile() {
//    	if (!backupExists)
//    		return;
//    	String fullBackupName = getAbsoluteName() + "/" + backupFeatureGradeFileName;
//    	File backup = new File(fullBackupName); 
//    	if (!retVal) return false;
//    	String fullFeatureName = getAbsoluteName() + "/" + featureGradeFileName;
//    	File original = new File (fullFeatureName);
//    	   	
//    	original.renameTo(backup);
//    	return true;   	
//    	
//    }
    


}
