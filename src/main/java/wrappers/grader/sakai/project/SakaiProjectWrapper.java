package wrappers.grader.sakai.project;

import framework.project.Project;
import wrappers.grader.file.SimplifiedFileProxy;
import grader.language.LanguageDependencyManager;
import grader.project.file.ARootCodeFolder;
import grader.sakai.ASakaiStudentCodingAssignment;
import grader.sakai.StudentCodingAssignment;
import grader.sakai.project.ASakaiProject;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProject;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class SakaiProjectWrapper extends ASakaiProject {
    public SakaiProjectWrapper(Project project) {
    
//        super(getCodingAssignment(project), ".java", ".txt");
        super(getCodingAssignment(project), LanguageDependencyManager.getSourceFileSuffix(), ".txt");


        // Set it as run so we can do stuff without actually running it
        setCanBeRun(true);
        setHasBeenRun(true);
    }
    // so is this code executed when we are using Josh's stuff?
    private static StudentCodingAssignment getCodingAssignment(Project project) {
        File folder = project.getSourceFolder();
        while (!folder.getName().matches("^[^,]+, [^\\(]+\\([^\\)]+\\)$"))
            folder = folder.getParentFile();
        // it seems this is executed only when Josh's framework is  used, so we will use his file proxy
        return new ASakaiStudentCodingAssignment(folder.getName(), new SimplifiedFileProxy(folder));
//        return new ASakaiStudentCodingAssignment(folder.getName(), ASakaiProjectDatabase.getCurrentSakaiProjectDatabase().getStudentAssignmentDatabase().getBulkAssignmentFolder().getStudentFolder(folder.getName()));

    }
}
