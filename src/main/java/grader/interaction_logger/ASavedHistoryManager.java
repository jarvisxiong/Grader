package grader.interaction_logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ASavedHistoryManager {
	String interactionDirectory;
	File[] interactionFiles;
	List<SavedAllStudentsProblemGradingHistory> problemHistory = new ArrayList();
	
	public void readInteractionDirectory() {
		interactionDirectory = AnInteractionLogWriter.getOrCreateInteractionFolder();
		interactionFiles = FileTimeSorterAndComparator.sort(new File(interactionDirectory));
	}
	
	public void buildHistories() {
		buildProblemHistories();
		
	}
	
	public void buildProblemHistories() {
		
	}
	
	public void buildStudentHistories() {
		
	}
	
	


}
