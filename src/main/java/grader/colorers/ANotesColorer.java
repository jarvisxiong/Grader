package grader.colorers;

import grader.sakai.project.SakaiProjectDatabase;

import java.awt.Color;

public class ANotesColorer implements Colorer<String>{
	SakaiProjectDatabase database; // context for coloring

	public ANotesColorer(SakaiProjectDatabase aDatabase) {
		
		database = aDatabase;
	}

	@Override
	public Color color(String aColorable) {
		if (aColorable != null && !aColorable.isEmpty() )
			return Color.GREEN;
		return null;
	}

}
