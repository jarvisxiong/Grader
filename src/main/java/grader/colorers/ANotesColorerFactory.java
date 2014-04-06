package grader.colorers;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProjectDatabase;

public class ANotesColorerFactory implements ColorerFactory<String> {

	@Override
	public Colorer<String> createColorer(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return new ANotesColorer(aSakaiProjectDatabase);
	}

}
