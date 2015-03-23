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
	
	public static NotesStatus fromString(String aString) {
		if (aString.equals("Needs Notes"))
			return NEEDS_NOTES;
		else if (aString.equals("Has Some Notes"))
			return HAS_NOTES;
		else if (aString.equals("Has No Notes"))
			return NotesStatus.NO_NOTES;
		else if (aString.equals("ALL"))
			return ALL;
		return null;
	}

}
