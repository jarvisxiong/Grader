package grader.assignment;

import util.models.ListenableVector;


public interface GradingFeatureList extends ListenableVector<GradingFeature> {
    public boolean isAllGraded();
    public boolean isAllAutoGraded();
	boolean hasSomeNotes();
	boolean hasASelection();
	boolean isSomeAutoGraded();

}
