package grader.spreadsheet.csv;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import grader.assignment.AnAssignmenDataFolder;
import grader.file.FileProxy;
import grader.file.filesystem.AFileSystemFileProxy;
import grader.sakai.project.ProjectStepperFactory;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class AnAllStudentsHistoryManager implements AllStudentsHistoryManager{
	SakaiProjectDatabase projectDataBase;
	String historyFolderName;
	public static final String STUDENT_HISTORY_FOLDER = "StudentHistory";

	Map<String, IndividualStidentHistoryManager> onyenToSpreadsheet = new HashMap();
	public AnAllStudentsHistoryManager(SakaiProjectDatabase aProjectDatabase) {
		projectDataBase = aProjectDatabase;
		historyFolderName = projectDataBase.getAssignmentDataFolder().getMixedCaseAbsoluteName() +
				"/" + STUDENT_HISTORY_FOLDER;
//		ProjectStepperFactory.getProjectStepper().addPropertyChangeListener(this);
		ProjectStepperFactory.
			getProjectStepper().
				getGradedProjectOverview().
					getTextOverview().
						addPropertyChangeListener(this);		

	}
	
	protected IndividualStidentHistoryManager getOrCreateSpreadsheet(String anOnyen){
		File aFolder = new File (historyFolderName);
		if (!aFolder.exists()) {
			aFolder.mkdirs();
		}
		IndividualStidentHistoryManager result = onyenToSpreadsheet.get(anOnyen);
		if (result == null) {
			String aFileName = historyFolderName
					+ "/" + anOnyen 
					+ ".csv";
			File aFile = new File(aFileName);
			if (!aFile.exists()) {
				String aFeatureSpreadSheetName =  projectDataBase.
						getAssignmentDataFolder().getFeatureGradeFile().getMixedCaseAbsoluteName();
				File aFetaureSpreadsheet = new File(aFeatureSpreadSheetName);
                try {
					Files.copy(aFetaureSpreadsheet.toPath(), aFile.toPath(), REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        

			}
            FileProxy aFileProxy = new AFileSystemFileProxy(aFile);
            result = new AnIndividualStudentHistoryManager(aFileProxy, projectDataBase.getGradingFeatures());
            onyenToSpreadsheet.put(anOnyen, result); 
		}
		return result;
	}
	
	public void setGrade(String aStudentName, String anOnyen, String aFeature, double aScore) {
		// this should always be get
		IndividualStidentHistoryManager aManager = getOrCreateSpreadsheet(anOnyen);
		aManager.setGrade(aStudentName, anOnyen, aFeature, aScore);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!"onyen".equalsIgnoreCase(evt.getPropertyName())) {
			return;
			
		}
//		SakaiProject aProject = (SakaiProject) evt.getNewValue();
//		String anOnyen = aProject.
		String anOnyen = (String) evt.getNewValue();
		IndividualStidentHistoryManager aManager = getOrCreateSpreadsheet(anOnyen);
		System.out.println("Property change event:" + evt);
		aManager.addNewRow();

		
	}

}
