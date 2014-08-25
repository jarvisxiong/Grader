package framework.grading.testing;

import framework.project.Project;
import grader.trace.feature.FeatureChecked;

import java.util.List;

import util.trace.Tracer;

/**
 * The idea for this class is that features and restrictions both check their test cases. This handles that process.
 */
public abstract class Checkable implements Gradable {
    public boolean manual; // added by pd

    
    public boolean isManual() {
        return manual;
    }

    /**
     * This checks the test cases against the project
     *
     * @param points    The max points to award
     * @param testCases The test cases to test
     * @param project   The project the test cases will be checked against
     * @return The results of the check
     */
    protected CheckResult check(double points, List<TestCase> testCases, Project project, boolean autoMode) {
    	if (isManual())
    		 return new CheckResult(0, "", CheckResult.CheckStatus.NotGraded, this);
        if (testCases.isEmpty())
            return new CheckResult(0, "", CheckResult.CheckStatus.Failed, this);
        double pointWeight = points / testCases.size();
        CheckResult result = new CheckResult(pointWeight, this);
        try {
            for (TestCase testCase : testCases) {
                TestCaseResult testResult = testCase.test(project, autoMode);
                result.save(testResult);
            }
            result.setStatus(CheckResult.CheckStatus.Successful);
            FeatureChecked.newCase(null, null, null, this, this);
            return result;
        } catch (NotAutomatableException e) {
        	e.announce();
//            return new CheckResult(0, "", CheckResult.CheckStatus.NotGraded, this);
            return new CheckResult(0, "Not automatable", CheckResult.CheckStatus.NotGraded, this);

        } catch (NotGradableException e) {
        	e.announce();
        	String msg;
        	if (e.getMessage() == null || e.getMessage().isEmpty())
        		msg = "Grading failed";
        	else
        		msg = "Grading failed: " + e.getMessage();
//        	String msg = "Grading failed: " + e.getMessage();
//        	String msg = "Grading failed";


//        	String msg = "Could not grade because did not find classes ";
//        	Tracer.error("Could not grade because did not find classes ");
        	Tracer.error(msg);
//            e.printStackTrace();
            return new CheckResult(0, msg, CheckResult.CheckStatus.Failed, this);
        } catch (Exception e) {
        	NotGradableException.newCase(e.getMessage(), this);
        	e.printStackTrace();
            return new CheckResult(0, "Not gradable", CheckResult.CheckStatus.NotGraded, this);

        }
    }


    public CheckResult check(Project project) {
        return check(project, true);
//        return check(project, !isManual());

    }

    /**
     * This is the publicly available check method, to be implemented by the extender
     *
     * @param project The project to check
     * @param autoMode If we are auto grading, that is, to display GUIs/user interaction
     * @return The results of the check
     */
    public abstract CheckResult check(Project project, boolean autoMode);


}