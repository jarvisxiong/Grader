package grader.navigation.filter;

import util.annotations.Explanation;

@Explanation("Needs notes")
public enum NotesStatus {
	NEEDS_NOTES ("Needs Notes"),
	HAS_NOTES ("Has Some Notes"),
	NO_NOTES ("Has No Notes"),
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
