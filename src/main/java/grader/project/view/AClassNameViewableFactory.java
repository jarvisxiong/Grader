package grader.project.view;

import grader.project.flexible.FlexibleClassDescription;

public class AClassNameViewableFactory implements ViewableClassDescriptionFactory<String>{
	// chooses a class viewer
	@Override
	public ViewableClassDescription createViewable(FlexibleClassDescription aClassDescription) {
		return new AClassNameViewable(aClassDescription);
	}

}
