package gradingTools.comp410s16.assignment2.testcases;

import java.util.Set;

//import scala.Option;









import wrappers.framework.project.ProjectWrapper;
import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.util.Option;
import grader.sakai.project.SakaiProject;

/**
 * Created by andrewwg94 on 2/11/16.
 */
public class FunctionalityTestCase extends BasicTestCase {
    public FunctionalityTestCase() {
        super("Functional Correctness Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            SakaiProject aProject = ((ProjectWrapper) project).getProject();
            aProject.getClassesTextManager().getAllSourcesText();
            Option<ClassesManager> classesManager = project.getClassesManager();

            System.out.println("class descriptions: "+classesManager.get().getClassDescriptions());
            Set<ClassDescription> cd = classesManager.get().findByClassOrInterfaceName(classesManager.get().getClassDescriptions().toArray()[0].toString());
            Class<String> queue = (Class<String>) cd.iterator().next().getJavaClass();

            String[] input = new String[4];
            input[0] = "greek";
            input[1] = "alpha";
            input[2] = "beta";
            input[3] = "gamma";

            // Initialize score and feedback message
            String message = "";

            RunningProject runningProject = project.launch("", input,10);

            String output=runningProject.await().toLowerCase();
            System.out.println("output: "+output);
            boolean correct = output.contains("1\n" +
                    "1\n"+
                    "greek\n"+
                    "greek\n"+
                    "0\n"+
                    "0\n"+
                    "alpha\n"+
                    "alpha\n"+
                    "2\n"+
                    "2\n"+
                    "beta\n"+
                    "beta\n"+
                    "1\n"+
                    "1\n"+
                    "gamma\n"+
                    "gamma\n"+
                    "0\n"+
                    "0");
            System.out.println("correct: "+correct);

            if(correct){
                return pass();
            }
            else{
                return partialPass(.5, message);
            }

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}
