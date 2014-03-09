package grader.navigation.filter;

public enum GradingStatus {
	NOT_FULLY_GRADED ("Not Fully Graded"),
	FULLY_GRADED ("Fully Graded"),
	NOT_FULLY_AUTO_GRADED ("Not Auto Graded"),
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
