package grader.settings.navigation;

public enum NotesStatus {
	NOTES ("Has Notes"),
	NO_NOTES ("No Notes"),
	ALL ("All"),;
	
	// display name constructor and variable
	
	String name;
	NotesStatus(String aName) {
		name = aName;		
	}
	public String toString() {
		return name;
	}

}
