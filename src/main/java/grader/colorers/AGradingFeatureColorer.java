package grader.colorers;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProjectDatabase;

import java.awt.Color;

public class AGradingFeatureColorer implements Colorer<GradingFeature>{
	SakaiProjectDatabase database;
	public AGradingFeatureColorer(SakaiProjectDatabase aSakaiProjectDatabase) {
		database = aSakaiProjectDatabase;
	}
	@Override
	public Color color(GradingFeature aGradingFeature) {
		if (!aGradingFeature.getNotes().isEmpty())
			return Color.GREEN;	
		// most manual extra credit will not be graded 
		if (!aGradingFeature.isGraded() && aGradingFeature.isExtraCredit())
			return Color.BLUE;
		if (!aGradingFeature.isGraded())
			return Color.RED;
		// put some notes if these conditions hold
		 if (aGradingFeature.isAutoWithNotFullCredit() ||
				 aGradingFeature.isManualWithNotFullCredit() ) 
			return Color.PINK;
		
		return null;
	}


}
