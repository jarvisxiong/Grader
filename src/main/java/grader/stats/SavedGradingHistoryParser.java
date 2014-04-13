package grader.stats;

public interface SavedGradingHistoryParser {

	public abstract SavedAllStudentsProblemGradingHistory parseAllStudentsProblemGradingHistory(String aFileName);

	public abstract void parseNavigation();

	public abstract boolean hasMoreNavigations();

	public abstract boolean hasMoreVisits();

	public abstract void processNextVisit();

}