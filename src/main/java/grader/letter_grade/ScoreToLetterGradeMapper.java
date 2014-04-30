package grader.letter_grade;

public interface ScoreToLetterGradeMapper {
	public LetterGrade toCoarseLetterGrade (double aScore, double aMaxValue);	
    public LetterGrade toCoarseLetterGrade (double aScore) ;

}
