package grader.project.view;

import grader.project.flexible.FlexibleClassDescription;

public class AnUnsortingViewableFactory implements ViewableClassDescriptionFactory<String>{

	@Override
	public ViewableClassDescription createViewable(FlexibleClassDescription aClassDescription) {
		return new AnUnsortingViewable(aClassDescription);
	}

}
