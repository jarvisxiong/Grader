package framework.grading.testing;

import framework.project.Project;
import grader.permissions.Permissible;

/**
 * Like AnAbstractFeatureChecker
 */
public interface TestCase extends Permissible{

    /**
     * @return A name or short description about the test case.
     */
    public String getName();

    /**
     * This tests the project to see if somethings is the way it is supposed to be
     *
     * @param project The project to test
     * @return A {@link TestCaseResult} containing the result and any notes.
     * @throws NotAutomatableException
     */
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException;

    /**
     * Sets the checkable containing this test case.
     *
     * @param checkable The check to set as the owner.
     * @deprecated This is never used and does nothing.
     */
    @Deprecated
    public void setCheckable(Checkable checkable);
}
