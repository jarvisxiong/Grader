package framework.logging;

import framework.grading.testing.CheckResult;

import java.util.List;

/**
 * A class that can be converted to JSON.
 */
public class JsonWritableResults {

    private String userId;
    private List<CheckResult> featureResults;
    private List<CheckResult> restrictionResults;
    private String comments;
    private double latePenalty;

    public JsonWritableResults(String userId, List<CheckResult> featureResults, List<CheckResult> restrictionResults,
                               String comments, double latePenalty) {
        this.userId = userId;
        this.featureResults = featureResults;
        this.restrictionResults = restrictionResults;
        this.comments = comments;
        this.latePenalty = latePenalty;
    }

    public String getUserId() {
        return userId;
    }

    public List<CheckResult> getFeatureResults() {
        return featureResults;
    }

    public List<CheckResult> getRestrictionResults() {
        return restrictionResults;
    }

    public String getComments() {
        return comments;
    }

    public double getLatePenalty() {
        return latePenalty;
    }
}
