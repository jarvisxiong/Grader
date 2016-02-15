package gradingTools.comp410s16.assignment2.testcases;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import gradingTools.utils.RunningProjectUtils;
import scala.Option;
import wrappers.framework.project.ProjectWrapper;

import java.lang.reflect.InvocationTargetException;

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
            List<ClassDescription> cd = classesManager.get().findByClassName("BridgeQueueListDemo");
            Class<String> queue = (Class<String>) cd.get(0).getJavaClass();

            String[] input = new String[4];
            input[0] = "greek";
            input[1] = "alpha";
            input[2] = "beta";
            input[3] = "gamma";

            // Initialize score and feedback message
            double score = 1;
            String message = "";

            RunningProject runningProject = project.launch("", input,10);

            String output=runningProject.await().toLowerCase();
            System.out.println("output: "+output);

            //Get Stack Class
            Object queueInstance = null;
            try {
                queueInstance = queue.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                e1.printStackTrace();
            }

            Method main = null;

            //Extract methods
            for (Method method : queue.getDeclaredMethods()) {
               if(method.getName().equals("main")) {
                    main = method;
                }
            }

//            final Object[] args = new Object[1];
//            args[0] = new String[] {input[0]};
//            try{
//                if(main!=null) {
//                    String[] i =  (String[]) main.invoke(queueInstance, args);
//                    System.out.println("result: " +i);
//                }
//            } catch (Exception e) {
//                message += "Main throws error \n";
//            }



            if(score == 1){
                return pass();
            }
            else{
                return partialPass(score, message);
            }

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}
