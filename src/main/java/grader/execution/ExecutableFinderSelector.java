package grader.execution;

public class ExecutableFinderSelector {
	static MainClassFinder mainClassFinder = new AnExecutableFinder();

	public static MainClassFinder getMainClassFinder() {
		return mainClassFinder;
	}

	public static void setMainClassFinder(MainClassFinder mainClassFinder) {
		ExecutableFinderSelector.mainClassFinder = mainClassFinder;
	}

}
