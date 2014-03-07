package grader.colorers;

import java.awt.Color;

import grader.letter_grade.AScoreToCoarseLetterGradeMapper;
import grader.letter_grade.CoarseLetterGrade;
import grader.letter_grade.ScoreToCoarseLetterGradeMapper;
import grader.sakai.project.SakaiProjectDatabase;

public class AScoreColorer implements Colorer<Double>{
	SakaiProjectDatabase database; // context for coloring
	public static final Color LIGHT_GREEN = new Color(144, 238, 144);
	public static final Color DARK_GREEN = new Color(0, 100, 0);
	double maxValue;
	double notAThreshold = 0.9;
	double failThreshold = 0.6;
	Color notAColor = Color.PINK;
	Color moreThanFullCreditColor = Color.GREEN;
	Color failColor = Color.RED;
	ScoreToCoarseLetterGradeMapper mapper = new AScoreToCoarseLetterGradeMapper(); // use a factory to unite it with navigation?
	
	public AScoreColorer(SakaiProjectDatabase aDatabase, double aMaxValue) {
		database = aDatabase;
		maxValue = aMaxValue;
	}
	@Override
	public Color color(Double aNum) {
		CoarseLetterGrade grade = mapper.toCoarseLetterGrade(aNum, maxValue);
		switch (grade) {
		case A: return Color.GREEN;
		case B: return LIGHT_GREEN;
		case C: return Color.YELLOW;
		case D: return Color.PINK;
		case F: return Color.RED;
		}
		return null;
		
				
	}

}
