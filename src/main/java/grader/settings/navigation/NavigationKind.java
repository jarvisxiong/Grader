package grader.settings.navigation;

public enum NavigationKind  {
	AUTOMATIC ("Automatic"),
	MANUAL ("Manual"),
	AUTOMATIC_THEN_MANUAL ("Automatic, then Manual");
	
	// display name constructor and variable
	
	String name;
	NavigationKind(String aName) {
		name = aName;		
	}
	public String toString() {
		return name;
	}

}
