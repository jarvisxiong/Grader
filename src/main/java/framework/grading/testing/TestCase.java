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
    
    public void setName(String aName);

    /**
     * This tests the project to see if somethings is the way it is supposed to be
     *
     * @param project The project to test
     * @return A {@link TestCaseResult} containing the result and any notes.
     * @throws NotAutomatableException
     */
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException;

   
    public void setCheckable(Checkable checkable);

	Checkable getCheckable();
	 public double getPointWeight() ;
	public void setPointWeight(double pointWeight) ;
}
