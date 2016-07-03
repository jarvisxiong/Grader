package grader.execution;

import grader.basics.execution.MainClassFinder;

public class ExecutableFinderSelector {
	static MainClassFinder mainClassFinder = new AnExecutableFinder();

	public static MainClassFinder getMainClassFinder() {
		return mainClassFinder;
	}

	public static void setMainClassFinder(MainClassFinder mainClassFinder) {
		ExecutableFinderSelector.mainClassFinder = mainClassFinder;
	}

}
