package grader.file;

import grader.file.filesystem.AFileSystemRootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;
import grader.project.AProject;

public class ARootFolderCreator implements RootFolderCreator {
    public  RootFolderProxy createRootFolder(String aFolder) {
        boolean isZipperFolder = aFolder.endsWith(AProject.ZIP_SUFFIX_1) || aFolder.endsWith(AProject.ZIP_SUFFIX_2);
        if (isZipperFolder) {
            return new AZippedRootFolderProxy(aFolder);
        } else {
            return new AFileSystemRootFolderProxy(aFolder);
        }
    }

}
