package grader.colorers;

import grader.sakai.project.SakaiProjectDatabase;

public class NotesColorerSelector {
	static ColorerFactory<String> factory = new ANotesColorerFactory();

	public static ColorerFactory<String> getFactory() {
		return factory;
	}

	public static void setFactory(ColorerFactory<String> factory) {
		NotesColorerSelector.factory = factory;
	}
	
	public static Colorer<String> createColorer(SakaiProjectDatabase aSakaiProjectDatabase) {
		return factory.createColorer(aSakaiProjectDatabase);
		
	}

}
