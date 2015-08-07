package grader.checkers;

import framework.grading.testing.TestCaseResult;
import java.util.List;

public interface CheckResult {
    public double getScore();

    public void setLog(List<String> log);

    public void setScore(double score);

    public List<String> getLog();

	String getAutoNotes();

	void setAutoNotes(String autoNotes);
        
    public void setResults(List<TestCaseResult> results);
    public List<TestCaseResult> getResults();

}
