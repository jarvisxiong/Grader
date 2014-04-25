package grader.interaction_logger.manual_grading_stats;

import java.util.ArrayList;
import java.util.List;

public class AStudentAllProblemsHistory implements StudentAllProblemsHistory {
	String onyen;
	String name;
	List<StudentProblemHistory> problemHistories = new ArrayList();
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#getOnyen()
	 */
	@Override
	public String getOnyen() {
		return onyen;
	}
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#setOnyen(java.lang.String)
	 */
	@Override
	public void setOnyen(String onyen) {
		this.onyen = onyen;
	}
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#getProblemHistories()
	 */
	@Override
	public List<StudentProblemHistory> getProblemHistories() {
		return problemHistories;
	}
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#setProblemHistories(java.util.List)
	 */
	@Override
	public void setProblemHistories(
			List<StudentProblemHistory> problemHistories) {
		this.problemHistories = problemHistories;
	}
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedStudentAllProblemsGradingHistory#addSavedStudentProblemGradingHistory(grader.stats.SavedStudentProblemGradingHistory)
	 */
	@Override
	public void addSavedStudentProblemGradingHistory(StudentProblemHistory newValue) {
		problemHistories.add(newValue);
	}
	


}
