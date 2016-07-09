package grader.spreadsheet.csv;

import grader.sakai.project.SakaiProjectDatabase;

public class AllStudentsHistoryManagerFactory {
	static AllStudentsHistoryManager singleton;
	public static AllStudentsHistoryManager getOrCreateAllStudentsHistoryManager(SakaiProjectDatabase aProjectDatabase) {
		if (singleton ==null) {
			singleton = new AnAllStudentsHistoryManager(aProjectDatabase);
		}
		return singleton;
		
	}
	public static AllStudentsHistoryManager getAllStudentsHistoryManager() {		
		return singleton;		
	}

}
