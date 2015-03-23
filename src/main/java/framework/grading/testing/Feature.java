package framework.grading.testing;

import framework.project.Project;

import java.util.Arrays;
import java.util.List;

/**
 * Features are aspects of the program that it "should" have.
 * @see Restriction
 */
public class Feature extends Checkable {
	

//    private String name;
//    private double points;
//    private boolean extraCredit;
//    public boolean manual; // added by pd
//    private List<TestCase> testCases;

//    public Feature(String name, double points, List<TestCase> testCases) {
//        this.name = name;
//        this.points = points;
//        this.extraCredit = false;
//        this.testCases = testCases;
//    }
//
//    public Feature(String name, double points, boolean extraCredit, List<TestCase> testCases) {
//        this.name = name;
//        this.points = points;
//        this.extraCredit = extraCredit;
//        this.testCases = testCases;
//    }
//    
//    public Feature(boolean anIsManual, String name, double points, boolean extraCredit, List<TestCase> testCases) {
//      this(name, points, extraCredit, testCases);
//      manual = anIsManual;
//    }
//    public Feature(boolean anIsManual, String name, double points, boolean extraCredit, TestCase ... testCases) {
//        this(name, points, extraCredit, testCases);
//        manual = anIsManual;
//    }
//    
//    public Feature(String name, double points, TestCase ... testCases) {
//        this.name = name;
//        this.points = points;
//        this.extraCredit = false;
//        this.testCases = Arrays.asList(testCases);
//    }
//
//    public Feature(String name, double points, boolean extraCredit, TestCase ... testCases) {
//        this.name = name;
//        this.points = points;
//        this.extraCredit = extraCredit;
//        this.testCases = Arrays.asList(testCases);
//    }

//    @Override
//    public String getName() {
//        return name;
//    }

//    @Override
//    public double getPoints() {
//        return points;
//    }
//
//    public boolean isExtraCredit() {
//        return extraCredit;
//    }
    
   

    public Feature(boolean anIsManual, String name, double points,
			boolean extraCredit, List<TestCase> testCases) {
		super(anIsManual, name, points, extraCredit, testCases);
		// TODO Auto-generated constructor stub
	}

	public Feature(boolean anIsManual, String name, double points,
			boolean extraCredit, TestCase... testCases) {
		super(anIsManual, name, points, extraCredit, testCases);
		// TODO Auto-generated constructor stub
	}

	public Feature(String name, double points, boolean extraCredit,
			List<TestCase> testCases) {
		super(name, points, extraCredit, testCases);
		// TODO Auto-generated constructor stub
	}

	public Feature(String name, double points, boolean extraCredit,
			TestCase... testCases) {
		super(name, points, extraCredit, testCases);
		// TODO Auto-generated constructor stub
	}

	public Feature(String name, double points, List<TestCase> testCases) {
		super(name, points, testCases);
		// TODO Auto-generated constructor stub
	}

	public Feature(String name, double points, TestCase... testCases) {
		super(name, points, testCases);
		// TODO Auto-generated constructor stub
	}

	@Override
    public CheckResult check(Project project, boolean autoMode) {
        return check(points, testCases, project, autoMode);
    }

    @Override
    public String getSummary() {
        String spaces = "                                                       ";
        String score = points < 10 ? " " + points : points + "";
        String ec = extraCredit ? " (Extra credit)" : "";
        return name + spaces.substring(name.length()) + "%.1f / " + score + ec;
    }
    public String toString() {
    	return name;
    }
}
