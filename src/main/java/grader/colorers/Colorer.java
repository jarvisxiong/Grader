package grader.colorers;

import grader.assignment.GradingFeature;

import java.awt.Color;

public interface Colorer<Colorable> {
	Color color(Colorable aColorable);


}
