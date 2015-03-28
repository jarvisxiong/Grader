package grader.interaction_logger.manual_grading_stats;

import java.io.File;
import java.util.List;

public interface GradingHistoryParser {


	public abstract AllStudentsProblemHistory parseAllStudentsProblemGradingHistory(String aFileName);

	public abstract void parseNavigation();

	public abstract boolean hasMoreNavigations();

	public abstract boolean hasMoreVisits();

	public abstract void processNextVisit();

	StudentProblemHistory parseStudentHistory(List<String[]> aTable);

	boolean initNewNavigation(List<String[]> aTable);

	AllStudentsProblemHistory parseAllStudentsProblemGradingHistory(File file);

}