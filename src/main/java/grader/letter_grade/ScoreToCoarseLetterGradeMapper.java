package grader.letter_grade;

public interface ScoreToCoarseLetterGradeMapper {
	public CoarseLetterGrade toCoarseLetterGrade (double aScore, double aMaxValue);	
    public CoarseLetterGrade toCoarseLetterGrade (double aScore) ;

}
