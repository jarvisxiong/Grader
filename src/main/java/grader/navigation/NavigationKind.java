package grader.navigation;

public enum NavigationKind  {
	AUTOMATIC ("Automatic"),
	MANUAL ("Manual"),
	HYBRID ("Automatic and then manual");
	
	// display name constructor and variable
	
	String name;
	NavigationKind(String aName) {
		name = aName;		
	}
	public String toString() {
		return name;
	}

}
