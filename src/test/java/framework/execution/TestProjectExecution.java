package framework.execution;

import static org.junit.Assert.assertEquals;
import framework.execution.reflectionObjects.ManualProject;
import grader.basics.execution.NotRunnableException;
import grader.basics.project.Project;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/7/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestProjectExecution {

    @Test
    public void testRun() throws NotRunnableException {
        Project project = new ManualProject();
        ReflectionRunner runner = new ReflectionRunner(project);
        String output = runner.run("Testing").await();
        assertEquals("Run works", "Testing", output.trim());
    }

//    @Test
//    public void testLaunch() {
//
//
//    }
}
