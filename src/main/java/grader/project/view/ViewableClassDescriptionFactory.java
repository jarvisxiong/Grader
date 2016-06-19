package grader.project.view;

import grader.project.flexible.FlexibleClassDescription;

public interface ViewableClassDescriptionFactory<FilterType> {
	 ViewableClassDescription createViewable(FlexibleClassDescription aClassDescription);

}
