package grader.project;

public class JavaMainClassFinderSelector {
	static MainClassFinder mainClassFinder = new AMainClassFinder();

	public static MainClassFinder getMainClassFinder() {
		return mainClassFinder;
	}

	public static void setMainClassFinder(MainClassFinder mainClassFinder) {
		JavaMainClassFinderSelector.mainClassFinder = mainClassFinder;
	}

}
