package framework.grading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import wrappers.framework.project.ProjectWrapper;
import framework.execution.ARunningProject;
import framework.grading.testing.CheckResult;
import framework.grading.testing.Checkable;
import framework.grading.testing.Feature;
import framework.grading.testing.Restriction;
import framework.grading.testing.TestCase;
import grader.basics.execution.BasicProjectExecution;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.Project;
import grader.language.LanguageDependencyManager;
import grader.sakai.project.SakaiProject;

/**
 * This is the fundamental container which holds all the features and
 * restrictions on which programs are graded.
 */
public class FrameworkProjectRequirements implements ProjectRequirements {

    private List<Feature> features;
    private List<Restriction> restrictions;
//    protected Map<Object, Object> userData = new HashMap();
    private List<DueDate> dueDates = new ArrayList<DueDate>();

    /**
     * It's important that there is a nullary constructor because this needs to
     * be able to be simply instantiated via reflection.
     */
    public FrameworkProjectRequirements() {
        features = new ArrayList<Feature>();
        restrictions = new ArrayList<Restriction>();
    }

    // Feature adding methods
    public void addFeature(Feature feature) {
        features.add(feature);
        feature.initRequirements(this);
    }

    public void addFeature(String name, double points, List<TestCase> testCases) {
        addFeature(new Feature(name, points, testCases));
    }

    public void addFeature(String name, double points, boolean extraCredit, List<TestCase> testCases) {
        addFeature(new Feature(name, points, extraCredit, testCases));
    }

    public void addFeature(String name, double points, TestCase... testCases) {
        addFeature(new Feature(name, points, testCases));
    }

    public void addFeature(String name, double points, boolean extraCredit, TestCase... testCases) {
        addFeature(new Feature(name, points, extraCredit, testCases));
    }

    public void addFeature(boolean anIsManual, String name, double points, boolean extraCredit, TestCase... testCases) {
        addFeature(new Feature(anIsManual, name, points, extraCredit, testCases));
    }

    public void addRestriction(boolean anIsManual, String name, double points, boolean extraCredit, TestCase... testCases) {
        addRestriction(new Restriction(anIsManual, name, points, extraCredit, testCases));
    }

    public void addManualFeature(String name, double points, boolean extraCredit) {
        addFeature(new Feature(true, name, points, extraCredit,  new TestCase[]{}));
    }

    public void addManualFeature(String name, double points, TestCase... testCases) {
        addFeature(new Feature(true, name, points, false, testCases));
    }

    public void addManualFeature(String name, double points, boolean extraCredit, TestCase... testCases) {
        addFeature(new Feature(true, name, points, extraCredit, testCases));
    }
    

    // Restriction adding methods
    public void addRestriction(Restriction restriction) {
        restrictions.add(restriction);
        restriction.initRequirements(this);
    }

    public void addRestriction(String name, double points, TestCase... testCases) {
        addRestriction(new Restriction(name, points, testCases));
    }

    public void addRestriction(String name, double points, List<TestCase> testCases) {
        addRestriction(new Restriction(name, points, testCases));
    }
    public void addManualRestriction(String name, double points, boolean extraCredit, TestCase... testCases) {
        addRestriction(new Restriction(true, name, points, extraCredit, testCases));
    }
    public void addManualRestriction(String name, double points, TestCase... testCases) {
        addRestriction(new Restriction(true, name, points, false, testCases));
    }

    // Due date adding methods
    public void addDueDate(DateTime dateTime, double percentage) {
        dueDates.add(new DueDate(dateTime, percentage));
    }

    public void addDueDate(String dateTime, double percentage) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        DateTime dt = formatter.parseDateTime(dateTime);
        addDueDate(dt, percentage);
    }

    // Getters
    public List<Feature> getFeatures() {
        return features;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    // Grading methods
    /**
     * Given a project, this checks all the features against it.
     *
     * @param project The project to check, or grade.
     * @return A list of {@link CheckResult} corresponding to the features
     */
    public List<CheckResult> checkFeatures(Project project) {
//    	getUserData().clear();
    	clearUserObjects();
    	// need to do this once for all features and test cases to prserve caches
    	CurrentProjectHolder.setProject(project); // for Junit test cases
//    	BasicProjectIntrospection.clearProjectCaches(); // all th eclasses and methods cached
        List<CheckResult> results = new LinkedList<CheckResult>();
        SakaiProject sakaiProject = null;
        if (project instanceof ProjectWrapper) {
            ProjectWrapper projectWrapper = (ProjectWrapper) project;
            sakaiProject = projectWrapper.getProject();
        }
        for (Feature feature : features) {
//        	if (feature.isManual()) 
//        		continue;
//        	CurrentProjectHolder.setProject(project); // in case some feature reset the project
        	BasicProjectExecution.redirectOutput();
        	if (isInteractiveRun(feature))
        		results.add(feature.check(project, false)); // added again below
            if (sakaiProject != null) { // should we do the check anyway, regardless of whether sakaiProject is null or not
                sakaiProject.setCurrentGradingFeature(feature);
            
            try {
                results.add(feature.check(project)); //so the feature is added twice?
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            
        			String anOutput = BasicProjectExecution.restoreOutputAndGetRedirectedOutput();
        			 if (anOutput != null && !anOutput.isEmpty()) {
                     	ARunningProject.appendToTranscriptFile(project, feature.getName(), anOutput);
                     }
        			
        		
            }
            }
        }
        return results;
    }

    /**
     * Given a project, this checks all the restrictions against it.
     *
     * @param project The project to check, or grade.
     * @return A list of {@link CheckResult} corresponding to the restrictions
     */
    public List<CheckResult> checkRestrictions(Project project) {
        List<CheckResult> results = new LinkedList<CheckResult>();
        for (Restriction restriction : restrictions) {
        	if (isInteractiveRun(restriction))
        		results.add(restriction.check(project, false));
        	else
              results.add(restriction.check(project));
        }
        return results;
    }

    /**
     * Given a due date, this figures out what score modifier (a percentage)
     * should be given.
     *
     * @param dateTime The submission time of the project
     * @return A score modifier percentage
     */
    public double checkDueDate(DateTime dateTime) {
        if (dueDates.isEmpty()) {
            return 1;
        }
        double percentage = 0;
        for (DueDate dueDate : dueDates) {
            if (dueDate.getCutoffDate().isAfter(dateTime)) {
                percentage = Math.max(percentage, dueDate.getPercentage());
            }
        }
        return percentage;
    }

    @Override
    public Object[] getPermissions() {
        return LanguageDependencyManager.getDefaultPermissible().getPermissions();
    }
    @Override
    public Object getUserObject (Object aKey) {
//    	return userData.get(aKey);
    	return BasicProjectIntrospection.getUserObject(aKey);
    }
    @Override
    public void putUserObject (Object aKey, Object aValue) {
//    	 userData.put(aKey, aValue);
    	BasicProjectIntrospection.putUserObject(aKey, aValue);
    }
    boolean isInteractiveRun(Checkable aFeature) {
    	return aFeature.getName().equals(INTERACTIVE_RUN);
    }

	@Override
	public Feature getInteractiveRunFeature() {
		for (Feature aFeature:features) {
//			if (aFeature.getName().equals(INTERACTIVE_RUN))
			if (isInteractiveRun(aFeature))
				return aFeature;
		}
		return null;
	}
	@Override
	public Restriction getInteractiveRunRestriction() {
		for (Restriction aRestriction:restrictions) {
//			if (aFeature.getName().equals(INTERACTIVE_RUN))
			if (isInteractiveRun(aRestriction))
				return aRestriction;
		}
		return null;
	}

//	@Override
//	public Map<Object, Object> getUserData() {
//		return userData;
//	}
	@Override
	public void clearUserObjects() {
		BasicProjectIntrospection.clearUserObjects();
	}

}
