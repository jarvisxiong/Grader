package grader.file;

import grader.sakai.ASakaiGradeSpreadsheetExtractor;
import grader.sakai.GradeSpreadsheetExtractor;

public class RootFolderCreatorFactory {
	static RootFolderCreator singleton;
	public static RootFolderCreator getSingleton() {
		if (singleton == null) {
			singleton = new ARootFolderCreator();			
		}
		return singleton;
	}

}
