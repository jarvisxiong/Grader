package grader.settings.navigation;

public enum GradingStatus {
	ALL ("All Submitted"),
	NOT_AUTO_GRADED ("Not Auto Graded"),
	NOT_GRADED ("Not Fully Graded"),
	GRADED ("Fully Graded");
	

	// display name constructor and variable
	
	String name;
	GradingStatus(String aName) {
		name = aName;		
	}
	public String toString() {
		return name;
	}

}
