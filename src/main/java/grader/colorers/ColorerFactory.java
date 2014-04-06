package grader.colorers;

import grader.sakai.project.SakaiProjectDatabase;


public interface ColorerFactory<ElementType> {
	Colorer<ElementType> createColorer(SakaiProjectDatabase aSakaiProjectDatabase);

}
