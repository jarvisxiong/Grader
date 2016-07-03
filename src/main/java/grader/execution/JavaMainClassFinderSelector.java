package grader.execution;

import grader.basics.execution.MainClassFinder;

public class JavaMainClassFinderSelector {
	static MainClassFinder mainClassFinder = new ABasicMainClassFinder();

	public static MainClassFinder getMainClassFinder() {
		return mainClassFinder;
	}

	public static void setMainClassFinder(MainClassFinder mainClassFinder) {
		JavaMainClassFinderSelector.mainClassFinder = mainClassFinder;
	}

}
