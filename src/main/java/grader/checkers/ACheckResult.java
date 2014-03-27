package grader.checkers;

import java.util.ArrayList;
import java.util.List;

public class ACheckResult implements CheckResult{
	List<String> log = new ArrayList();
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
}
