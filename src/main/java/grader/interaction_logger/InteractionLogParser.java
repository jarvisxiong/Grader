package grader.interaction_logger;

public interface InteractionLogParser {


	public abstract AllStudentsProblemHistory parseAllStudentsProblemGradingHistory(String aFileName);

	public abstract void parseNavigation();

	public abstract boolean hasMoreNavigations();

	public abstract boolean hasMoreVisits();

	public abstract void processNextVisit();

}