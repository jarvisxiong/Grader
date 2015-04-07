package grader.sakai;

import grader.file.RootFolderProxy;

public interface AssignmentFolderExtractor {

	public abstract RootFolderProxy extractAssignmentFolder(
			RootFolderProxy aRootBulkDownloadFolder, String anAssignmentName);

}