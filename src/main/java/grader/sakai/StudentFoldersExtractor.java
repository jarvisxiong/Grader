package grader.sakai;

import grader.file.RootFolderProxy;

import java.util.Comparator;
import java.util.Set;

public interface StudentFoldersExtractor {

	public abstract Set<String> extractStudentFolderNames(
			RootFolderProxy aRootBulkDownloadFolder,
			RootFolderProxy anAssignmentFolder,
			Comparator<String> aFileNameComparator);

}