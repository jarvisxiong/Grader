package grader.letter_grade;

public class AScoreToCoarseLetterGradeMapper implements ScoreToCoarseLetterGradeMapper {
	public static double A_THRESHOLD = 0.9;
	public static double B_THRESHOLD = 0.8;
	public static double C_THRESHOLD = 0.7;
	public static double D_THRESHOLD = 0.6;
	
	public CoarseLetterGrade toCoarseLetterGrade (double aScore, double aMaxValue) {
		double percent = aScore/aMaxValue;
		if (percent >= A_THRESHOLD)
			return CoarseLetterGrade.A;
		if (percent >= B_THRESHOLD)
			return CoarseLetterGrade.B;
		if (percent >= C_THRESHOLD)
			return CoarseLetterGrade.C;
		if (percent >= D_THRESHOLD)
			return CoarseLetterGrade.D;
		return CoarseLetterGrade.F;
			
		
	}
	
    public CoarseLetterGrade toCoarseLetterGrade (double aScore) {
		return toCoarseLetterGrade(aScore, 100);
	}

}
