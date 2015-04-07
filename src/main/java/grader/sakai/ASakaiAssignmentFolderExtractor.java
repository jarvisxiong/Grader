package grader.sakai;

import grader.file.FileProxy;
import grader.file.RootFolderProxy;

import java.util.Set;

public class ASakaiAssignmentFolderExtractor implements AssignmentFolderExtractor {
	 /* (non-Javadoc)
	 * @see grader.sakai.SakaiAssignmentFolderExtractor#extractAssignmentFolder(grader.file.RootFolderProxy, java.lang.String)
	 */
	@Override
	public RootFolderProxy extractAssignmentFolder(RootFolderProxy aRootBulkDownloadFolder, String anAssignmentName){
//	    	if (isAssignmentRoot) return rootBulkDownloadFolder;
	        Set<String> childrenNames = aRootBulkDownloadFolder.getChildrenNames();
	        for (String childName : childrenNames) {
	            FileProxy fileProxy = aRootBulkDownloadFolder.getFileEntry(childName);
	            if (fileProxy.isDirectory()) return fileProxy;
	        }
	        return aRootBulkDownloadFolder.getFileEntry(aRootBulkDownloadFolder.getAbsoluteName() + "/" + anAssignmentName);
	    }
}
