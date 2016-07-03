package framework.project;

import java.util.Map;

import util.pipe.InputGenerator;
import framework.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.project.ClassesManager;
import grader.basics.project.Project;

public interface FatProject extends Project{
	/**
     * Attempts to start the project in the same process
     */
//    public RunningProject start(String input) throws NotRunnableException;

//    /**
//     * Attempts to launch the project in a new process
//     */
//    public RunningProject launch(String input) throws NotRunnableException;

    /**
     * Attempts to start the project in the same process
     */
    public RunningProject start(String input, int timeout) throws NotRunnableException;

//    /**
//     * Attempts to launch the project in a new process
//     */
//    public RunningProject launch(InputGenerator anOutputBasedInputGenerator, String input, int timeout) throws NotRunnableException;
//
//    public RunningProject launch(String input, int timeout) throws NotRunnableException;

//    public RunningProject launchInteractive() throws NotRunnableException;

//    RunningProject launch(
//			InputGenerator anOutputBasedInputGenerator,
//			Map<String, String> aProcessToInput, int timeout)
//			throws NotRunnableException;
//
//	RunningProject launch(String input, String[] anArgs, int timeout)
//			throws NotRunnableException;

	RunningProject launchInteractive(String[] args) throws NotRunnableException;

}
