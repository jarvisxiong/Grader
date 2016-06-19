package grader.project.view;

import grader.project.flexible.FlexibleClassDescription;

public interface ViewableClassDescription<FilterType> extends /*Viewable<ClassDescription, String>,*/ Filterable<String>, Comparable<ViewableClassDescription> {
	public FlexibleClassDescription getClassDescription() ;
	public void setClassDescription(FlexibleClassDescription classDescription);
	public boolean filter(String aRegExp);

}
