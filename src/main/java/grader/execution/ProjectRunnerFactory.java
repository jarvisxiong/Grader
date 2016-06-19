package grader.execution;

import grader.project.flexible.FlexibleProject;

import java.lang.reflect.Method;

public interface ProjectRunnerFactory {

    public Runnable createProjectRunner(String aMainClassName, String[][] aMainArgs, FlexibleProject aProject, String[] anInputFiles, String[] anOutputFiles, Class aMainClass, Method aMainMethod);

}
