package grader.execution;

import grader.project.flexible.FlexibleProject;

import java.lang.reflect.Method;

public class AReflectionBasedProjectRunnerFactory implements ProjectRunnerFactory {

    @Override
    public Runnable createProjectRunner(String aMainClassName,
                                        String[][] aMainArgs, FlexibleProject aProject, String[] anInputFiles,
                                        String[] anOutputFiles, Class aMainClass, Method aMainMethod) {

        return new AReflectionBasedProjectRunner(aMainClassName, aMainArgs, aProject, anInputFiles, anOutputFiles,
                aMainClass, aMainMethod);
    }

}
