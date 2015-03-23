package grader.colorers;

import grader.sakai.project.SakaiProjectDatabase;

public class MultiplierColorerSelector {
	static ColorerFactory<Double> factory = new AScoreColorerFactory(1.0);

	public static ColorerFactory<Double> getFactory() {
		return factory;
	}

	public static void setFactory(ColorerFactory<Double> factory) {
		MultiplierColorerSelector.factory = factory;
	}
	
	public static Colorer<Double> createColorer(SakaiProjectDatabase aSakaiProjectDatabase) {
		return factory.createColorer(aSakaiProjectDatabase);
		
	}

}
