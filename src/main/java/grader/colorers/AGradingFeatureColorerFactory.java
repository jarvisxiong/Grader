package grader.colorers;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProjectDatabase;

public class AGradingFeatureColorerFactory implements ColorerFactory<GradingFeature> {

	@Override
	public Colorer<GradingFeature> createColorer(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return new AGradingFeatureColorer(aSakaiProjectDatabase);
	}

}
