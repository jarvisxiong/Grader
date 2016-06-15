package framework.grading.testing;

import java.util.List;

import framework.project.Project;

/**
 * Restrictions are aspects of the program that it "should not" have.
 * @see Feature
 */
public class Restriction extends Checkable {

//    private String name;
//    private double points;
//    private List<TestCase> testCases;

//    public Restriction(String name, double points, List<TestCase> testCases) {
//        this.name = name;
//        this.points = Math.min(points, -points); // Make sure we are negative
//        this.testCases = testCases;
//    }
//
//    public Restriction(String name, double points, TestCase ... testCases) {
//        this.name = name;
//        this.points = Math.min(points, -points); // Make sure we are negative
//        this.testCases = Arrays.asList(testCases);
//    }

//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public double getPoints() {
//        return points;
//    }

    /**
     * Checks the project for restrictions. Negative points are given if violated.
     *
     * @param project The project to check
     * @param autoMode Whether or not we are "auto-grading"
     * @return The result of the restriction checks
     */
    @Override
    public CheckResult check(Project project, boolean autoMode) {
        CheckResult result = check(-points, testCases, project, autoMode);
        result.setScore(result.getScore() + points);
        return result;
    }
    
    void setPoints() {
      points = Math.min(points, -points); // Make sure we are negative

    }

    public Restriction(boolean anIsManual, String name, double points,
			boolean extraCredit, List<TestCase> testCases) {
		super(anIsManual, name, points, extraCredit, testCases);
		setPoints();
		// TODO Auto-generated constructor stub
	}

	public Restriction(boolean anIsManual, String name, double points,
			boolean extraCredit, TestCase... testCases) {
		super(anIsManual, name, points, extraCredit, testCases);
		setPoints();
	}

	public Restriction(String name, double points, boolean extraCredit,
			List<TestCase> testCases) {
		super(name, points, extraCredit, testCases);
		setPoints();
	}

	public Restriction(String name, double points, boolean extraCredit,
			TestCase... testCases) {
		super(name, points, extraCredit, testCases);
		setPoints();
	}

	public Restriction(String name, double points, List<TestCase> testCases) {
		super(name, points, testCases);
		setPoints();
	}

	public Restriction(String name, double points, TestCase... testCases) {
		super(name, points, testCases);
		setPoints();
	}

	@Override
    public String getSummary() {
        String spaces = "                                            ";
        String score = points < 10 ? " " + points : points + "";
        return name + spaces.substring(name.length()) + "%.1f / " + score;
    }
	@Override
	public Object[] getPermissions() {
		return getRequirements().getPermissions();
	}
}
