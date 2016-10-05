package wrappers.grader.checkers;

import wrappers.framework.project.ProjectWrapper;
import framework.grading.testing.Checkable;
import framework.logging.recorder.ConglomerateRecorder;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.settings.BasicGradingEnvironment;
import grader.checkers.ACheckResult;
import grader.checkers.CheckResult;

/**
 * This wraps a {@link Checkable} in a feature checker so as to handle the exceptions.
 */
public class FeatureCheckerWrapper extends ErrorHandlingFeatureChecker {

    private Checkable checkable;

    public FeatureCheckerWrapper(Checkable checkable) {
        this.checkable = checkable;
        setOverridable(false);
    }

    @Override
    protected CheckResult doCheck() throws Exception {

        Project project = new ProjectWrapper(this.project, BasicGradingEnvironment.get().getAssignmentName());
        CurrentProjectHolder.setProject(project);
//        framework.grading.testing.CheckResult checkResult = checkable.check(project, false);
        framework.grading.testing.CheckResult checkResult = checkable.check(project, true);
        ConglomerateRecorder.getInstance().save(checkResult);
        
        CheckResult result = new ACheckResult();

        // Check the test status. Only accept successes
        if (checkResult.getStatus() != framework.grading.testing.CheckResult.CheckStatus.Successful)
            throw new NotGradableException();

        result.setScore(checkResult.getScore());
        result.setResults(checkResult.getResults());

        // Add general notes
        if (!checkResult.getNotes().isEmpty()) {
            String autoNotes = checkResult.getNotes();
            result.getLog().add(autoNotes);
            result.setAutoNotes(autoNotes);
        }

        // Add notes from test case results
        for (TestCaseResult testCaseResult : checkResult.getResults()) {
            if (!testCaseResult.getNotes().isEmpty())
                result.getLog().add(testCaseResult.getNotes());
        }
        
        return result;
    }
}
