package main;

import grail.demo.Assignment12Demo;
import grail.demo.TwelveDemo;
import grail.view.BasicProgressView;
import grail.view.ProgressView;


public class Assignment12 {
	
	public static void main(String[] args) {
		final int progressViewX = 1200;
		final int progressViewY = 825;
		TwelveDemo assignmentDemo = new Assignment12Demo();		
		ProgressView progress = new BasicProgressView();
		assignmentDemo.addPropertyChangeListener(progress);
		progress.setLocation(progressViewX, progressViewY);
		progress.bringToFront();
		
		assignmentDemo.run();
	}
	
}
