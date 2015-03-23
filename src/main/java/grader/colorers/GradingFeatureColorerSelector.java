package grader.colorers;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProjectDatabase;

public class GradingFeatureColorerSelector {
	static ColorerFactory<GradingFeature> factory = new AGradingFeatureColorerFactory();

	public static ColorerFactory<GradingFeature> getFactory() {
		return factory;
	}

	public static void setFactory(ColorerFactory<GradingFeature> factory) {
		GradingFeatureColorerSelector.factory = factory;
	}
	
	public static Colorer<GradingFeature> createColorer(SakaiProjectDatabase aSakaiProjectDatabase) {
		return factory.createColorer(aSakaiProjectDatabase);
		
	}

}
