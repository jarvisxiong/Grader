package grader.file;

import grader.file.filesystem.AFileSystemRootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;
import grader.project.AProject;

public class ARootFolderCreator implements RootFolderCreator {
    public  RootFolderProxy createRootFolder(String aFolder) {
        boolean isZipperFolder = aFolder.endsWith(AProject.ZIP_SUFFIX_1) || aFolder.endsWith(AProject.ZIP_SUFFIX_2);
        
        if (isZipperFolder) {
//        	System.out.println ("Found zipped folder:" + aFolder + " with siffx2 " + AProject.ZIP_SUFFIX_2);
            return new AZippedRootFolderProxy(aFolder);
        } else {
            return new AFileSystemRootFolderProxy(aFolder);
        }
    }

}
