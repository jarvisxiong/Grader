package grader.letter_grade;


public enum LetterGrade {
	A,
	B,
	C,
	D,
	F;
	public static LetterGrade fromString(String aString) {
		if (aString.equals("A"))
			return A;
		else if (aString.equals("B"))
			return B;
		else if (aString.equals("C"))
			return C;
		else if (aString.equals("D"))
			return D;
		else if (aString.equals("F"))
			
			return F;
		return null;
	}
	

}
