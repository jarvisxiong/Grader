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
	public static GradingStatus fromString(String aString) {
		if (aString.equals( NOT_FULLY_GRADED.toString()))
			return NOT_FULLY_GRADED;
		else if (aString.equals(FULLY_GRADED.toString()))
			return FULLY_GRADED;
		else if (aString.equals(NOT_FULLY_AUTO_GRADED.toString()))
			return GradingStatus.NOT_FULLY_AUTO_GRADED;
		else if (aString.equals("ALL"))
			return ALL;
		return null;
	}

}
