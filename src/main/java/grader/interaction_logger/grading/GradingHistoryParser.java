package grader.interaction_logger.grading;

import java.util.List;

public interface GradingHistoryParser {


	public abstract AllStudentsProblemHistory parseAllStudentsProblemGradingHistory(String aFileName);

	public abstract void parseNavigation();

	public abstract boolean hasMoreNavigations();

	public abstract boolean hasMoreVisits();

	public abstract void processNextVisit();

	StudentProblemGradingHistory parseStudentHistory(List<String[]> aTable);

}