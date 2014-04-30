package grader.letter_grade;

public class AScoreToLetterGradeMapper implements ScoreToLetterGradeMapper {
	public static double A_THRESHOLD = 0.9;
	public static double B_THRESHOLD = 0.8;
	public static double C_THRESHOLD = 0.7;
	public static double D_THRESHOLD = 0.6;
	
	public LetterGrade toCoarseLetterGrade (double aScore, double aMaxValue) {
		double percent = aScore/aMaxValue;
		if (percent >= A_THRESHOLD)
			return LetterGrade.A;
		if (percent >= B_THRESHOLD)
			return LetterGrade.B;
		if (percent >= C_THRESHOLD)
			return LetterGrade.C;
		if (percent >= D_THRESHOLD)
			return LetterGrade.D;
		return LetterGrade.F;
			
		
	}
	
    public LetterGrade toCoarseLetterGrade (double aScore) {
		return toCoarseLetterGrade(aScore, 100);
	}

}
