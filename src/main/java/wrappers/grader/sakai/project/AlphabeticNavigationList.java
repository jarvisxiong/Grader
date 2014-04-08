package wrappers.grader.sakai.project;

import edu.emory.mathcs.backport.java.util.Arrays;
import framework.utils.GraderSettings;
import grader.navigation.sorter.AFileObjectSorter;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.NavigationListCreator;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.file.sakai_bulk_folder.StudentFolderNamesSorted;
import grader.trace.stepper.navigation.NavigationListCreated;

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
		
		
        
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().contains("(" + GraderSettings.get().get("start") + ")"))
                    include = true;
                if (include)
                    onyens.add(file.getName().substring(file.getName().indexOf("(") + 1, file.getName().indexOf(")")));
                if (file.getName().contains("(" + GraderSettings.get().get("end") + ")")) {
                    include = false;
                    break;
                }
            }
        }
        if (include) { // did not find ending onyen
        	onyens.clear(); // maybe should throw OnyenRangeError rather than let caller throw it
        }
		NavigationListCreated.newCase(aSakaiProjectDatabase, (OverviewProjectStepper) aSakaiProjectDatabase.getProjectStepper(), aSakaiProjectDatabase.getProjectStepper().getProject(), onyens, this);
		
        return onyens;
    }
}
