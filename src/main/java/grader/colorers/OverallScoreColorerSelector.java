package grader.colorers;

import grader.sakai.project.SakaiProjectDatabase;

public class OverallScoreColorerSelector {
	static ColorerFactory<Double> factory = new AScoreColorerFactory(100.0);

	public static ColorerFactory<Double> getFactory() {
		return factory;
	}

	public static void setFactory(ColorerFactory<Double> factory) {
		OverallScoreColorerSelector.factory = factory;
	}
	
	public static Colorer<Double> createColorer(SakaiProjectDatabase aSakaiProjectDatabase) {
		return factory.createColorer(aSakaiProjectDatabase);
		
	}

}
