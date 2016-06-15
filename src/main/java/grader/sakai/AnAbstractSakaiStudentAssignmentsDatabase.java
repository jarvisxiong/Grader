package grader.sakai;

import framework.navigation.SakaiStudentFolder;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;
import grader.settings.folders.OnyenRangeModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import util.trace.Tracer;

public abstract class AnAbstractSakaiStudentAssignmentsDatabase<GenericAssignment> implements GenericStudentAssignmentDatabase<GenericAssignment> {
    BulkAssignmentFolder bulkAssignmentFolder;
    Map<String, GenericAssignment> nameToStudentAssignment = new HashMap();

    abstract protected GenericAssignment createAssignment(String anInd, String aFolderName);

    public AnAbstractSakaiStudentAssignmentsDatabase(BulkAssignmentFolder aBulkAssignmentFolder) {
        bulkAssignmentFolder = aBulkAssignmentFolder;
        makeTable();
    }

    void makeTable() {
    	SakaiProjectDatabase aProjectDatabase = ASakaiProjectDatabase.getCurrentSakaiProjectDatabase();
//    	List<String> onyens = aProjectDatabase.getOnyenNavigationList();    	
    	GraderSettingsModel aGraderSettingsModel = GraderSettingsModelSelector.getGraderSettingsModel();
    	OnyenRangeModel anOnyenRangeModel = aGraderSettingsModel.getOnyens();
    	String aStartOnyen = anOnyenRangeModel.getStartingOnyen();
    	String anEndOnyen = anOnyenRangeModel.getEndingOnyen();
    	String aGotoOnyen = anOnyenRangeModel.getGoToOnyen();
        Set<String> studentFolderNames = bulkAssignmentFolder.getStudentFolderNames();
        for (String aFolderName : studentFolderNames) {
            try {
                String studentId = Common.shortFileName(aFolderName);
                if (aFolderName.contains("._")) {
                	continue;
                }
                String anOnyen = SakaiStudentFolder.getOnyen(studentId);
                if (! (anOnyen.equals(aGotoOnyen) ||
                		anOnyen.compareTo(aStartOnyen) >= 0 &&
                		anOnyen.compareTo(anEndOnyen) <= 0))
                	continue;
              
                Tracer.info(this, "Folder:" + aFolderName);

                GenericAssignment studentAssignment = createAssignment(studentId, aFolderName); // this part should be called independenly in new process
                nameToStudentAssignment.put(studentId, studentAssignment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BulkAssignmentFolder getBulkAssignmentFolder() {
        return bulkAssignmentFolder;
    }

    public Set<String> getStudentIds() {
        return nameToStudentAssignment.keySet();

    }

    public Collection<GenericAssignment> getStudentAssignments() {
        return nameToStudentAssignment.values();
    }

    public GenericAssignment getStudentAssignment(String aStudentId) {
        return nameToStudentAssignment.get(aStudentId);
    }
}
