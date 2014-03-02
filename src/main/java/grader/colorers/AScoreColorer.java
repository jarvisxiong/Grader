package grader.colorers;

import java.awt.Color;

import grader.sakai.project.SakaiProjectDatabase;

public class AScoreColorer implements Colorer<Double>{
	SakaiProjectDatabase database; // context for coloring
	double maxValue;
	double notAThreshold = 0.9;
	double failThreshold = 0.6;
	Color notAColor = Color.PINK;
	Color moreThanFullCreditColor = Color.GREEN;
	Color failColor = Color.RED;
	
	public AScoreColorer(SakaiProjectDatabase aDatabase, double aMaxValue) {
		database = aDatabase;
		maxValue = aMaxValue;
	}
	@Override
	public Color color(Double aNum) {
		if (aNum > maxValue)
			return moreThanFullCreditColor;
		else if (aNum < maxValue*notAThreshold )
			return notAColor;
		else if (aNum < maxValue*failThreshold) 
			return failColor;
		else
			return null;			
	}

}
