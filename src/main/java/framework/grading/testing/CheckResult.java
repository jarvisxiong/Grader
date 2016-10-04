package framework.grading.testing;

import grader.basics.junit.TestCaseResult;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * This represents the result of doing some "check" on the project.
 */
@JsonIgnoreProperties({"status"})
public class CheckResult implements Describable {

    public enum CheckStatus {Successful, NotGraded, Failed}

    // Values that we will want later
    private double score;
    private List<TestCaseResult> results;
    private String notes;
    private Gradable target;
    String name;

    // Values for grading, not needed later
    private CheckStatus status;
    private double pointWeight;
    private double totalPoints;
    String autoNotes = "";

    

	/**
     * Use this constructor before processing any test results.
     * If you are going to change the values, use the setters.
     * @param pointWeight How many points each test case is worth.
     * @param target The gradable feature/restriction
     */
    public CheckResult(double aTotalPoints, double pointWeight, Gradable target) {
        this.pointWeight = pointWeight;
        this.target = target;
        totalPoints = aTotalPoints;
        score = 0;
        results = new ArrayList<TestCaseResult>();
        notes = "";
        status = CheckStatus.NotGraded;
        name = "";
//        if (pointWeight < 0) {
//        	
//        }
    }

    /**
     * Use this constructor to create custom results
     * @param score The score to set
     * @param notes General notes
     * @param status The final status
     * @param target The gradable feature/restriction
     */
    public CheckResult(double score, String notes, CheckStatus status, Gradable target) {
        this.score = score;
        this.notes = notes;
//        if (!notes.isEmpty())
//        	autoNotes += notes;
        this.status = status;
        this.target = target;

        results = new ArrayList<TestCaseResult>();
    }

    /**
     * @return The total score
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets the total score
     * @param score The score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return General notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets general notes about the check
     * @param notes The notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String toString() {
		return  name + ":" + score + " " + autoNotes;
	}

    /**
     * @return The grading status
     */
    public CheckStatus getStatus() {
        return status;
    }
    
    // copied code from the web
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * Sets the grading status.
     * @param status The new status
     */
    public void setStatus(CheckStatus status) {
        this.status = status;
        score = round(score, 1); // added by pd, this method seems to be called after all test cases have been run
    }

    /**
     * @return The detailed results from the test cases.
     */
    public List<TestCaseResult> getResults() {
        return results;
    }

    public void setResults(List<TestCaseResult> results) {
        this.results = results;
    }

    /**
     * @return The gradable Feature/Restriction
     */
    public Gradable getTarget() {
        return target;
    }

    public void setTarget(Gradable target) {
        this.target = target;
    }

    /**
     * Saves a result and updates the score based on that result.
     * @param result The result to save and process
     */
    public void save(TestCaseResult result) {
//    	if (result == null) return;
//    	String testNotes =  result.getNotes();
//    	if (!testNotes.isEmpty())
//    		autoNotes += "  -- " + result.getNotes() + "\n";
//        score += result.getPercentage() * pointWeight;
//        results.add(result);
    	save(result, pointWeight);
    }
    public void save(TestCaseResult result, double aPointWeight) {
    	if (aPointWeight < 0) {
    		aPointWeight = pointWeight;
    	}
    	if (result == null) return;
    	String testNotes =  result.getNotes();
    	if (!testNotes.isEmpty())
    		autoNotes += "  -- " + result.getNotes() + "\n";
        score += result.getPercentage() * aPointWeight * totalPoints;
//        if (score < 0 || result.getPercentage() < 0) {
//        	System.out.println ("Negative Score");
//        }
        results.add(result);
        name += result.getName() + " ";
    }
    
    public String getMessage() {
    	 String message = "";
         for (TestCaseResult testResult : getResults()) {
             message += testResult.getName() + ": " + (testResult.getPercentage() * 100) + "% \n";
             if (!testResult.getNotes().isEmpty())
                 message += "  -- " + testResult.getNotes() + "\n";
         }
         return message;
    }

    @Override
    public String getSummary() {
        String summary = "";
        for (TestCaseResult result : results) {
            if (!result.getNotes().isEmpty())
                summary += "\n * From test case \"" + result.getName() + "\": " + result.getNotes();
        }
        if (!notes.isEmpty())
            summary += "\n * Other notes: " + notes;
        if (summary.isEmpty())
            return summary;
        else
            return "Notes about " + target.getName() + ":" + summary;
    }
    
    public String getAutoNotes() {
		return autoNotes;
	}

	public void setAutoNotes(String autoNotes) {
		this.autoNotes = autoNotes;
	}
	public String getName() {
		return name;
	}

}
