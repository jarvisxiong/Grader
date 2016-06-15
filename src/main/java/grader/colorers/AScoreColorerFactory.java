package grader.colorers;

import grader.sakai.project.SakaiProjectDatabase;

public class AScoreColorerFactory implements ColorerFactory<Double> {
	Double maxValue;
	public AScoreColorerFactory(Double aMaxValue) {
		maxValue = aMaxValue;
	}

	@Override
	public Colorer<Double> createColorer(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return new AScoreColorer(aSakaiProjectDatabase, maxValue);
	}

}
