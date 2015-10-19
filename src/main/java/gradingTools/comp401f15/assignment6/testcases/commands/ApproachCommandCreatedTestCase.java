package gradingTools.comp401f15.assignment6.testcases.commands;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.comp401f15.assignment4.testcases.ScannerBeanReturnsTokenInterfaceArrayTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.*;


/**
 *
 * @author Andrew Vitkus
 */
public class ApproachCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "approach";
    public static final String REGEX_TAG = "[aA]pproach";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + REGEX_TAG + ".*", ".*" + REGEX_TAG + ".*"};};
    protected String commandName() { return "approach";}
        
    public ApproachCommandCreatedTestCase() {
        super("Approach Command Token Created Test Case");
    }

    @Override
    public TestCaseResult test(Project p, boolean autoGrade) {
        new ScannerBeanReturnsTokenInterfaceArrayTestCase().test(p, autoGrade);
        return super.test(p, autoGrade);
    }
}
