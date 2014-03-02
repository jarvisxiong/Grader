package grader.colorers;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProjectDatabase;

import java.awt.Color;

public class AGradingFeatureColorer implements Colorer<GradingFeature>{
	SakaiProjectDatabase database;
	// not making these constants to subclass can change them
	Color noNotesColor = Color.GREEN;
	Color notGradedExtraCreditColor = Color.BLUE;
	Color notGradedColor = Color.RED;
	Color gradedNonFullCreditColor = Color.PINK;

	public AGradingFeatureColorer(SakaiProjectDatabase aSakaiProjectDatabase) {
		database = aSakaiProjectDatabase;
	}
	@Override
	public Color color(GradingFeature aGradingFeature) {
		if (!aGradingFeature.getNotes().isEmpty())
			return noNotesColor;	
		// most manual extra credit will not be graded 
		if (!aGradingFeature.isGraded() && aGradingFeature.isExtraCredit())
			return notGradedExtraCreditColor;
		if (!aGradingFeature.isGraded())
			return notGradedColor;
		// put some notes if these conditions hold
		 if (aGradingFeature.isAutoWithNotFullCredit() ||
				 aGradingFeature.isManualWithNotFullCredit() ) 
			return gradedNonFullCreditColor;
		
		return null;
	}


}
