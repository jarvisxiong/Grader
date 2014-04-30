package grader.project;

public class MainClassFinderSelector {
	static MainClassFinder mainClassFinder = new AMainClassFinder();

	public static MainClassFinder getMainClassFinder() {
		return mainClassFinder;
	}

	public static void setMainClassFinder(MainClassFinder mainClassFinder) {
		MainClassFinderSelector.mainClassFinder = mainClassFinder;
	}

}
