package grader.navigation;

//import edu.emory.mathcs.backport.java.util.Arrays;
import framework.utils.GraderSettings;
import grader.navigation.sorter.AFileObjectSorter;
import grader.navigation.sorter.FileNameSorterSelector;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.AGraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;
import grader.steppers.OverviewProjectStepper;
import grader.trace.sakai_bulk_folder.StudentFolderNamesSorted;
import grader.trace.steppers.NavigationListCreated;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.misc.Common;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/12/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlphabeticNavigationListManager implements NavigationListManager {
	List<String> savedOnyens = null;
	String savedDirectoryName = null;
	String savedStartOnyen = null;
	String savedEndOnyen = null;
	String savedGoToOnyens = null;
	
	List<String> savedRawOnyens = null;
	String savedRawDirectoryName = null;
	String savedRawStartOnyen = null;
	String savedRawGoToOnyens = null;
	String savedRawEndOnyen = null;
	
	List<String> emptyOnyenList = new ArrayList();
	public static void trim (String[] aStrings) {
		for (int i = 0; i < aStrings.length; i++) {
			aStrings[i] = aStrings[i].trim();
		}
	}
	// this should go somehere else, maybe Grader settings model
	public static List<String> maybeGetGoToOnyenList() {
		String aGoToOnyen = GraderSettingsModelSelector.getGraderSettingsModel().getOnyens().getGoToOnyens();
    	if (aGoToOnyen != null && !aGoToOnyen.isEmpty()) {
    		String[] anOnyens = aGoToOnyen.split(",");
    		if (anOnyens.length > 0) {
    			trim(anOnyens);
    			Arrays.sort(anOnyens);
    			
    			return Arrays.asList(anOnyens); // we will navigate only to this list if more than one entry
    			// actually why autograde other assignments, let us autograde only go to ones.
    		}
    	}
    	return null;
	}
	public static boolean isGoToOnyenList() {
		return maybeGetGoToOnyenList() != null;
	}

	@Override
	// this is called only once so let us not do caching
    public List<String> getRawOnyenNavigationList() {
//    	List<String> aGoToOnyensList = maybeGetGoToOnyenList();
//    	if (aGoToOnyensList != null) {
//    		return aGoToOnyensList;
//    	}
		String aPath = GraderSettings.get().get("path");
		if (aPath == null) {
			System.err.println("Download path not specified");
			return null;
		}
//        File aDirectory = new File(GraderSettings.get().get("path"));
        File aDirectory = new File(aPath);

//        String aStartOnyen = GraderSettings.get().get("start");
//    	String anEndOnyen = GraderSettings.get().get("end");
    	String aStartOnyen = GraderSettings.get().get(AGraderSettingsModel.START_ONYEN);
    	String anEndOnyen = GraderSettings.get().get(AGraderSettingsModel.END_ONYEN);
    	String aGoToOnyen = GraderSettingsModelSelector.getGraderSettingsModel().getOnyens().getGoToOnyens();
//    	if (aStartOnyen == null ||
//    			aStartOnyen.isEmpty() ||
//    			anEndOnyen == null ||
//    			anEndOnyen.isEmpty() ||
//    			aDirectory.getName().isEmpty()) {
//    		System.out.println("Returning empty onyen list");
//    		return emptyOnyenList;
//    	}
    	if (
    		aStartOnyen.equals(savedRawStartOnyen) &&
    		anEndOnyen.equals(savedRawEndOnyen) &&
    		aGoToOnyen.equals(savedRawGoToOnyens) &&
    		aDirectory.getName().equals(savedRawDirectoryName)) {
    		return savedRawOnyens;
    	}
    	List<String> aGoToOnyensList = maybeGetGoToOnyenList();
    	
//    	savedRawStartOnyen = aStartOnyen;
//    	savedRawEndOnyen = anEndOnyen;
//    	savedRawDirectoryName = aDirectory.getName();    		
        List<String> anOnyens = new ArrayList<String>();
        if (aGoToOnyensList != null) {
    		anOnyens = aGoToOnyensList;
    	} else { // this should be in a separate method
	
        boolean include = false;
        File[] files = aDirectory.listFiles();
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
//		Arrays.sort(files, new AFileObjectSorter(aSakaiProjectDatabase.getFileNameSorter())) ;
		Arrays.sort(files, new AFileObjectSorter(FileNameSorterSelector.getSorter())) ;

		StudentFolderNamesSorted.newCase(Common.arrayToArrayList(files), this);
		
		
//    	String aStartOnyen = GraderSettings.get().get("start");
//    	String anEndOnyen = GraderSettings.get().get("end");
    	String aStartFilePart = "(" + aStartOnyen + ")";
    	String anEndFilePart = "(" + anEndOnyen + ")";
    	System.out.println("Searching for onyens between:" + aStartOnyen + "->" + anEndOnyen);
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
//                	SakaiProject aProject = aSakaiProjectDatabase.getProject(anOnyen);
//                	if (aProject == null || aProject.isNoProjectFolder()) {
////                		System.out.println("Onyen:" + anOnyen + " ignored because of missing project");
//                		;
//                	} else {
//                    onyens.add(file.getName().substring(file.getName().indexOf("(") + 1, file.getName().indexOf(")")));
                    anOnyens.add(anOnyen);
//                	}
                }
                if (file.getName().contains("(" + GraderSettings.get().get("end") + ")")) {
                	System.out.println ("Found end onyen:" + file.getName());
                    include = false;
                    break;
                }
            }
        }
        if (!foundStart) {
        	System.out.println ("Did not find start onyen:" +  aStartOnyen + " in:" + Arrays.toString(files));

        }
        if (include) { // did not find ending onyen
        	System.out.println ("Did not find end onyen:" + anEndOnyen + " in:" + Arrays.toString(files));

        	anOnyens.clear(); // maybe should throw OnyenRangeError rather than let caller throw it
        }
//        if (aSakaiProjectDatabase.getProjectStepper() != null)
//		NavigationListCreated.newCase(aSakaiProjectDatabase, (OverviewProjectStepper) aSakaiProjectDatabase.getProjectStepper(), aSakaiProjectDatabase.getProjectStepper().getProject(), anOnyens, this);
    	}
        savedRawOnyens = anOnyens;
		savedRawStartOnyen = aStartOnyen;
    	savedRawEndOnyen = anEndOnyen;
    	savedRawGoToOnyens = aGoToOnyen;
    	savedRawDirectoryName = aDirectory.getName(); 
        return anOnyens;
    }
	
	// the argument does not really matter now
    @Override
	public List<String> getOnyenNavigationList(
			SakaiProjectDatabase aSakaiProjectDatabase) {
    	File aDirectory = new File(GraderSettings.get().get("path"));
        String aStartOnyen = GraderSettings.get().get("start");
    	String anEndOnyen = GraderSettings.get().get("end");
    	String aGoToOnyen = GraderSettingsModelSelector.getGraderSettingsModel().getOnyens().getGoToOnyens();

//    	if (aStartOnyen == null ||
//    			aStartOnyen.isEmpty() ||
//    			anEndOnyen == null ||
//    			anEndOnyen.isEmpty() ||
//    			aDirectory.getName().isEmpty()) {
//    		System.out.println("Returning empty onyen list");
//    		return emptyOnyenList;
//    	}
    	if (
    		aStartOnyen.equals(savedStartOnyen) &&
    		anEndOnyen.equals(savedEndOnyen) &&
    		aGoToOnyen.equals(savedGoToOnyens) &&
    		aDirectory.getName().equals(savedDirectoryName)) {
    		return savedOnyens;
    	}
//    	savedStartOnyen = aStartOnyen;
//    	savedEndOnyen = anEndOnyen;
//    	savedDirectoryName = aDirectory.getName();    		
		List<String> anOnyens = getRawOnyenNavigationList();
		List<String> retVal = new ArrayList();
		for (String anOnyen : anOnyens) {

			SakaiProject aProject = aSakaiProjectDatabase.getProject(anOnyen);
			if (aProject != null && !aProject.isNoProjectFolder()) {
				// System.out.println("Onyen:" + anOnyen +
				// " ignored because of missing project");
				retVal.add(anOnyen);
			} else {
				System.out.println ("No project found for:" + anOnyen);
			}

		}

		if (aSakaiProjectDatabase.getProjectStepper() != null)
			NavigationListCreated.newCase(aSakaiProjectDatabase,
					(OverviewProjectStepper) aSakaiProjectDatabase
							.getProjectStepper(), aSakaiProjectDatabase
							.getProjectStepper().getProject(), anOnyens, this);
		savedOnyens = retVal;
	 	savedStartOnyen = aStartOnyen;
    	savedEndOnyen = anEndOnyen;
    	savedDirectoryName = aDirectory.getName(); 
    	savedGoToOnyens = aGoToOnyen;
		return retVal;
	}
//    public List<String> getOnyenNavigationList(SakaiProjectDatabase aSakaiProjectDatabase) {
//    	List<String> aGoToOnyensList = maybeGetGoToOnyenList();
//    	if (aGoToOnyensList != null) {
//    		return aGoToOnyensList;
//    	}
//        File aDirectory = new File(GraderSettings.get().get("path"));
//        String aStartOnyen = GraderSettings.get().get("start");
//    	String anEndOnyen = GraderSettings.get().get("end");
//    	if (aStartOnyen == null ||
//    			aStartOnyen.isEmpty() ||
//    			anEndOnyen == null ||
//    			anEndOnyen.isEmpty() ||
//    			aDirectory.getName().isEmpty()) {
//    		System.out.println("Returning empty onyen list");
//    		return emptyOnyenList;
//    	}
//    	if (
//    		aStartOnyen.equals(savedStartOnyen) &&
//    		anEndOnyen.equals(savedEndOnyen) &&
//    		aDirectory.getName().equals(savedDirectoryName)) {
//    		return savedOnyens;
//    	}
//    	savedStartOnyen = aStartOnyen;
//    	savedEndOnyen = anEndOnyen;
//    	savedDirectoryName = aDirectory.getName();    		
//        List<String> anOnyens = new ArrayList<String>();
//	
//        boolean include = false;
//        File[] files = aDirectory.listFiles();
////		Arrays.sort(files, new Comparator<Object>() {
////
////			@Override
////			public int compare(Object o1, Object o2) {
////				if (!(o1 instanceof File && o2 instanceof File)) {
////					throw new RuntimeException("Invalid Type.  Must be of type File.");
////				}
////				File f1 = (File) o1;
////				File f2 = (File) o2;
////				if (!f1.isDirectory() || !f2.isDirectory()) {
////					return f1.getName().compareTo(f2.getName());
////				}
////				String onyen1 = f1.getName().substring(f1.getName().lastIndexOf('(') + 1,
////						f1.getName().lastIndexOf(')'));
////				String onyen2 = f2.getName().substring(f2.getName().lastIndexOf('(') + 1,
////						f2.getName().lastIndexOf(')'));
////				return onyen1.compareTo(onyen2);
////			}
////		});
////		Arrays.sort(files, new AFileObjectSorter(aSakaiProjectDatabase.getFileNameSorter())) ;
//		Arrays.sort(files, new AFileObjectSorter(FileNameSorterSelector.getSorter())) ;
//
//		StudentFolderNamesSorted.newCase(Common.arrayToArrayList(files), this);
//		
//		
////    	String aStartOnyen = GraderSettings.get().get("start");
////    	String anEndOnyen = GraderSettings.get().get("end");
//    	String aStartFilePart = "(" + aStartOnyen + ")";
//    	String anEndFilePart = "(" + anEndOnyen + ")";
//    	System.out.println(" Searching for onyens between:" + aStartOnyen + "->" + anEndOnyen);
//    	boolean foundStart = false;
//
//        for (File file : files) {
//            if (file.isDirectory()) {
////                if (file.getName().contains("(" + GraderSettings.get().get("start") + ")"))
//                if (file.getName().contains(aStartFilePart)) {
//                    include = true;
//                	System.out.println ("Found start onyen:" + file.getName());
//                	foundStart = true;
//
//
//                }
//                if (include) {
////                	foundStart = true;
//                	String anOnyen = file.getName().substring(file.getName().indexOf("(") + 1, file.getName().indexOf(")"));
//                	SakaiProject aProject = aSakaiProjectDatabase.getProject(anOnyen);
//                	if (aProject == null || aProject.isNoProjectFolder()) {
////                		System.out.println("Onyen:" + anOnyen + " ignored because of missing project");
//                		;
//                	} else {
////                    onyens.add(file.getName().substring(file.getName().indexOf("(") + 1, file.getName().indexOf(")")));
//                    anOnyens.add(anOnyen);
//                	}
//                }
//                if (file.getName().contains("(" + GraderSettings.get().get("end") + ")")) {
//                	System.out.println ("Found end onyen:" + file.getName());
//                    include = false;
//                    break;
//                }
//            }
//        }
//        if (!foundStart) {
//        	System.out.println ("Did not find start onyen:" +  aStartOnyen + " in:" + Arrays.toString(files));
//
//        }
//        if (include) { // did not find ending onyen
//        	System.out.println ("Did not find end onyen:" + anEndOnyen + " in:" + Arrays.toString(files));
//
//        	anOnyens.clear(); // maybe should throw OnyenRangeError rather than let caller throw it
//        }
//        if (aSakaiProjectDatabase.getProjectStepper() != null)
//		NavigationListCreated.newCase(aSakaiProjectDatabase, (OverviewProjectStepper) aSakaiProjectDatabase.getProjectStepper(), aSakaiProjectDatabase.getProjectStepper().getProject(), anOnyens, this);
//		savedOnyens = anOnyens;
//        return anOnyens;
//    }
}
