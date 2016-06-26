package grader.sakai;

import framework.navigation.SakaiStudentFolder;
import grader.navigation.NavigationListManagerFactory;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;
import grader.settings.folders.OnyenRangeModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    	String aGotoOnyen = anOnyenRangeModel.getGoToOnyens();
        Set<String> studentFolderNames = bulkAssignmentFolder.getStudentFolderNames();
        createStudentAssinments(studentFolderNames);
        // we are duplicating the task done by alphbaetical navigator of Josh/Jacob, at some point we may want to disable this code 
//        Tracer.info (this, "Finding student ids");
//        for (String aFolderName : studentFolderNames) {
//            try {
//                String studentId = Common.shortFileName(aFolderName);
//                if (aFolderName.contains("._")) {
//                	continue;
//                }
//                String anOnyen = SakaiStudentFolder.getOnyen(studentId);
//                if (! (anOnyen.equals(aGotoOnyen) ||
//                		anOnyen.compareTo(aStartOnyen) >= 0 &&
//                		anOnyen.compareTo(anEndOnyen) <= 0))
//                	continue;
//              
//                Tracer.info(this, "Folder:" + aFolderName);
//
//                GenericAssignment studentAssignment = createAssignment(studentId, aFolderName); // this part should be called independenly in new process
//                nameToStudentAssignment.put(studentId, studentAssignment);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
    
    protected void createStudentAssinments( Set<String> studentFolderNames ) {
    	List<String> aRawOnyens = NavigationListManagerFactory.getNavigationListManager().getRawOnyenNavigationList();
    	 Set<String> aRawOnyenSet = new HashSet(aRawOnyens);
    	for (String aFolderName : studentFolderNames) {
             try {
                 String studentId = Common.shortFileName(aFolderName);
                 if (aFolderName.contains("._")) {
                 	continue;
                 }
                 String anOnyen = SakaiStudentFolder.getOnyen(studentId);
                 if (!aRawOnyenSet.contains(anOnyen)) {
                
                 	continue;
                 }
               
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
