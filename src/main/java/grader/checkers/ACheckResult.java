package grader.checkers;

import framework.grading.testing.TestCaseResult;
import java.util.ArrayList;
import java.util.List;

public class ACheckResult implements CheckResult {
	List<String> log = new ArrayList();
        private List<TestCaseResult> results = new ArrayList<>();
	String autoNotes = "";
	
	double score;

	public double getScore() {
		return score;
	}
	public void setLog(List<String> log) {
		this.log = log;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public List<String> getLog() {
		return log;
	}
	@Override
	public String getAutoNotes() {
		return autoNotes;
	}
	@Override
	public void setAutoNotes(String autoNotes) {
		this.autoNotes = autoNotes;
	}
        
        @Override
        public void setResults(List<TestCaseResult> results) {
            this.results = results;
        }
        
        @Override
        public List<TestCaseResult> getResults() {
            return results;
        }
}
