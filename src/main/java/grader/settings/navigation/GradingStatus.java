package grader.settings.navigation;

public enum GradingStatus {
	NOT_FULLY_GRADED ("Not Fully Graded"),
	FULLY_GRADED ("Fully Graded"),
	NOT_AUTO_GRADED ("Not Auto Graded"),
	ALL ("All");
	

	// display name constructor and variable
	
	String name;
	GradingStatus(String aName) {
		name = aName;		
	}
	public String toString() {
		return name;
	}

}
