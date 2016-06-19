package grader.execution;

import grader.project.flexible.FlexibleProject;

import java.lang.reflect.Method;

public class ProjectRunnerSelector {
    static ProjectRunnerFactory factory = new AReflectionBasedProjectRunnerFactory();

    public static ProjectRunnerFactory getFactory() {
        return factory;
    }

    public static void setFactory(ProjectRunnerFactory newVal) {
        factory = newVal;
    }

    public static Runnable createProjectRunner(String aMainClassName, String[][] aMainArgs, FlexibleProject aProject, String[] anInputFiles, String[] anOutputFiles, Class aMainClass, Method aMainMethod) {
        return factory.createProjectRunner(aMainClassName, aMainArgs, aProject, anInputFiles, anOutputFiles, aMainClass, aMainMethod);
    }

}
