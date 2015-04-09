package grader.sakai;

import grader.file.FileProxy;
import grader.file.RootFolderProxy;

public interface GradeSpreadsheetExtractor {
	public FileProxy extractGradeSpreadsheet(RootFolderProxy rootBulkDownloadFolder, RootFolderProxy assignmentFolder);

}