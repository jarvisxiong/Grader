package gradingTools;

import java.util.ArrayList;
import java.util.List;

import framework.grading.GradingMangerType;
// does not work right now with incremental args
public class HeadlessDriver {
	static String[] emptyStringArray = {};
	public static void main(String[] anArgs) {
		List<String> anArgsList = new ArrayList<>(15);
		fillCleanStateAll(anArgsList);
		fillController(anArgsList, GradingMangerType.HEADLESS_GRADING_MANAGER);
		String[] aDriverArgs =  anArgsList.toArray(emptyStringArray);
		Driver.main(aDriverArgs);
	}
	// copied from GraderConfigWriter in NIO grader server, code should probbaly go in util imported by both
	// all of these strings should be in a constants file in util
	public static String[] createCommandArgs(
			String aProjectRequirements,
			String anAssignmentName,
			String aController,
			String aPath,
			String aStartOnyen,
			String anEndOnyen,
			String aCourseName,
			Integer aLogging,
			Boolean aFrameworkGUI,
			Boolean isCleanState
			) {
        ArrayList<String> anArgsList = new ArrayList<>(15);
        if (aProjectRequirements != null) {
        anArgsList.add("--project-requirements");
        anArgsList.add(aProjectRequirements);
        }
        //LOG.log(Level.INFO, "project.requirements = {0}", projectRequirements);
        if (anAssignmentName != null) {
        anArgsList.add("--project-name ");
        anArgsList.add(anAssignmentName);
        }
        if (aController != null) {
        //LOG.log(Level.INFO, "project.name = {0}", assignmentName);
        anArgsList.add("--grader-controller");
        anArgsList.add(aController);
        }
        //LOG.log(Level.INFO, "grader.controller = {0}", controller);
        
        if (aPath != null) {
            anArgsList.add("--headless-path");
            anArgsList.add(aPath);
            //LOG.log(Level.INFO, "grader.headless.path = {0}", path);
        }
        if (aStartOnyen != null) {
            anArgsList.add("--headless-start");
            anArgsList.add(aStartOnyen);
            //LOG.log(Level.INFO, "\"grader.headless.start = {0}", startOnyen);
        }
        if (anEndOnyen != null) {
            anArgsList.add("--headless-end");
            anArgsList.add(anEndOnyen);
            //LOG.log(Level.INFO, "grader.headless.end = {0}", endOnyen);
        }
        if (aCourseName != null) {
            anArgsList.add("--course-name");
            anArgsList.add(aCourseName);
            //LOG.log(Level.INFO, "currentModule = {0}", courseName);
        }     
//        if (logging != 0) {
//            args.add("--logger");
//            args.add(getLoggingStr());
//            //LOG.log(Level.INFO, "grader.logger = {0}", getLoggingStr());
//        }
        if (aFrameworkGUI != null) {
            anArgsList.add("--use-framework-gui");
            //LOG.log(Level.INFO, "grader.controller.useFrameworkGUI = true");
        } else {
            anArgsList.add("--no-framework-gui");
            //LOG.log(Level.INFO, "grader.controller.useFrameworkGUI = false");
        }
        anArgsList.add("--clean-slate");
        anArgsList.add(aStartOnyen);

        return anArgsList.toArray(new String[anArgsList.size()]);
    }
	public static void fillAssignmentName(List<String> anArgsList,
			String anAssignmentName) {
			
		if (anAssignmentName != null) {
	        anArgsList.add("--project-name ");
	        anArgsList.add(anAssignmentName);
	        }
        
    }
	public static void fillProjectRequirements(List<String> anArgsList,
			String aProjectRequirements) {
			
        if (aProjectRequirements != null) {
        anArgsList.add("--project-requirements");
        anArgsList.add(aProjectRequirements);
        }        
    }
	
	public static void fillController(List<String> anArgsList,
			String aController) {
        if (aController != null) {
        //LOG.log(Level.INFO, "project.name = {0}", assignmentName);
        anArgsList.add("--grader-controller");
        anArgsList.add(aController);
        }
	}
	public static void fillPath(List<String> anArgsList,
			String aPath) {
        //LOG.log(Level.INFO, "grader.controller = {0}", controller);
        
        if (aPath != null) {
            anArgsList.add("--headless-path");
            anArgsList.add(aPath);
            //LOG.log(Level.INFO, "grader.headless.path = {0}", path);
        }
	}
	public static void fillStartEndOnyen(List<String> anArgsList,
			String aStartOnyen,
			String anEndOnyen) {
        if (aStartOnyen != null) {
            anArgsList.add("--headless-start");
            anArgsList.add(aStartOnyen);
            //LOG.log(Level.INFO, "\"grader.headless.start = {0}", startOnyen);
        }
        if (anEndOnyen != null) {
            anArgsList.add("--headless-end");
            anArgsList.add(anEndOnyen);
            //LOG.log(Level.INFO, "grader.headless.end = {0}", endOnyen);
        }
	}
	public static void fillCourseName(List<String> anArgsList,
			String aCourseName) {
        if (aCourseName != null) {
            anArgsList.add("--course-name");
            anArgsList.add(aCourseName);
            //LOG.log(Level.INFO, "currentModule = {0}", courseName);
        }  
	}
	public static void fillCleanStateAll(List<String> anArgsList) {
			
        anArgsList.add("--clean-slate");

    }

}
