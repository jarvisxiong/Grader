package grader.assignment;

import util.models.AListenableVector;
import util.annotations.Visible;

public class AGradingFeatureList extends AListenableVector<GradingFeature> implements GradingFeatureList{
	
	@Override
	public void open(GradingFeature element) {
		element.comment();
	}
	@Visible(false)
	public boolean isAllGraded() {
		for (GradingFeature gradingFeature:this) {
			if (! (gradingFeature.isGraded()) 
					|| (!gradingFeature.isFullCredit() && gradingFeature.getNotes().isEmpty())	
					
					)
					return false;
			
		}
		return true;
	}
	
	@Visible(false)
	@Override
	public boolean hasSomeNotes() {
		for (GradingFeature gradingFeature:this) {
			if (!gradingFeature.getNotes().isEmpty())				
					return true;
			
		}
		return false;
	}
	
	@Visible(false)
	public boolean isAllAutoGraded() {
		for (GradingFeature gradingFeature:this) {
			if (! (gradingFeature.isGraded() && gradingFeature.isAutoGradable()))
					return false;
		}
		return true;
	}

}
