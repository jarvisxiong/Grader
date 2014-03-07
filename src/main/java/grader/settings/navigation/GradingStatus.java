package grader.settings.navigation;

public enum GradingStatus {
	NOT_AUTO_GRADED ("Not Auto Graded"),
	NOT_GRADED ("Not Fully Graded"),
	GRADED ("Fully Graded"),
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
