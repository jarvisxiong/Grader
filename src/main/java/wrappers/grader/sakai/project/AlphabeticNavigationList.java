package wrappers.grader.sakai.project;

import edu.emory.mathcs.backport.java.util.Arrays;
import framework.utils.GraderSettings;
import grader.navigation.sorter.AFileObjectSorter;
import grader.sakai.project.NavigationListCreator;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.sakai_bulk_folder.StudentFolderNamesSorted;
import grader.trace.steppers.NavigationListCreated;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import util.misc.Common;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/12/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlphabeticNavigationList implements NavigationListCreator {
    @Override
    public List<String> getOnyenNavigationList(SakaiProjectDatabase aSakaiProjectDatabase) {
        List<String> onyens = new ArrayList<String>();
        File directory = new File(GraderSettings.get().get("path"));
        boolean include = false;
        File[] files = directory.listFiles();
//		Arrays.sort(files, new Comparator<Object>() {
//
//			@Override
//			public int compare(Object o1, Object o2) {
//				if (!(o1 instanceof File && o2 instanceof File)) {
//					throw new RuntimeException("Invalid Type.  Must be of type File.");
//				}
//				File f1 = (File) o1;
//				File f2 = (File) o2;
//				if (!f1.isDirectory() || !f2.isDirectory()) {
//					return f1.getName().compareTo(f2.getName());
//				}
//				String onyen1 = f1.getName().substring(f1.getName().lastIndexOf('(') + 1,
//						f1.getName().lastIndexOf(')'));
//				String onyen2 = f2.getName().substring(f2.getName().lastIndexOf('(') + 1,
//						f2.getName().lastIndexOf(')'));
//				return onyen1.compareTo(onyen2);
//			}
//		});
		Arrays.sort(files, new AFileObjectSorter(aSakaiProjectDatabase.getFileNameSorter())) ;
		StudentFolderNamesSorted.newCase(Common.arrayToArrayList(files), this);
		
		
    	String aStartOnyen = GraderSettings.get().get("start");
    	String anEndOnyen = GraderSettings.get().get("end");
    	String aStartFilePart = "(" + aStartOnyen + ")";
    	String anEndFilePart = "(" + anEndOnyen + ")";
    	System.out.println(" Searching for onyens between:" + aStartOnyen + "->" + anEndOnyen);
    	boolean foundStart = false;

        for (File file : files) {
            if (file.isDirectory()) {
//                if (file.getName().contains("(" + GraderSettings.get().get("start") + ")"))
                if (file.getName().contains(aStartFilePart)) {
                    include = true;
                	System.out.println ("Found start onyen:" + file.getName());
                	foundStart = true;


                }
                if (include) {
//                	foundStart = true;
                	String anOnyen = file.getName().substring(file.getName().indexOf("(") + 1, file.getName().indexOf(")"));
                	SakaiProject aProject = aSakaiProjectDatabase.getProject(anOnyen);
                	if (aProject == null || aProject.isNoProjectFolder()) {
//                		System.out.println("Onyen:" + anOnyen + " ignored because of missing project");
                		;
                	} else {
//                    onyens.add(file.getName().substring(file.getName().indexOf("(") + 1, file.getName().indexOf(")")));
                    onyens.add(anOnyen);
                	}
                }
                if (file.getName().contains("(" + GraderSettings.get().get("end") + ")")) {
                	System.out.println ("Found end onyen:" + file.getName());
                    include = false;
                    break;
                }
            }
        }
        if (!foundStart) {
        	System.out.println ("Did not find start onyen in:" + files);

        }
        if (include) { // did not find ending onyen
        	System.out.println ("Did not find end onyen in:" + files);

        	onyens.clear(); // maybe should throw OnyenRangeError rather than let caller throw it
        }
        if (aSakaiProjectDatabase.getProjectStepper() != null)
		NavigationListCreated.newCase(aSakaiProjectDatabase, (OverviewProjectStepper) aSakaiProjectDatabase.getProjectStepper(), aSakaiProjectDatabase.getProjectStepper().getProject(), onyens, this);
		
        return onyens;
    }
}
