package main;

import grail.demo.Assignment10Demo;
import grail.demo.NineDemo;
import grail.view.BasicProgressView;
import grail.view.ProgressView;


public class Assignment10 {
	
	public static void main(String[] args) {
		NineDemo assignmentDemo = new Assignment10Demo();
		ProgressView view = new BasicProgressView();
		assignmentDemo.addPropertyChangeListener(view);
		
		assignmentDemo.run();
	}
	
}
