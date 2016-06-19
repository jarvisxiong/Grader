package grader.project.view;

import grader.project.flexible.FlexibleClassDescription;

public class AnUnsortingViewable extends AViewableClassDescription<String> {
	public AnUnsortingViewable(FlexibleClassDescription aClassDescription) {
		super(aClassDescription);
	}
	@Override
	public int compareTo(ViewableClassDescription other) {
		return -1;
	}
	@Override
	public boolean filter(String aRegExp) {
		return true;
	}
//	@Override
//	public String getViewName() {
//		return ViewNames.CLASS_NAME_VIEW;
//	}	

}
