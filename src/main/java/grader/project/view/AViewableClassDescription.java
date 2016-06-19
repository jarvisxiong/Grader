package grader.project.view;

import grader.project.flexible.FlexibleClassDescription;
/*
 * The toString() method, implemented by its classes will be used to sort the class source
 * using the Collections sort method
 */
public abstract class AViewableClassDescription<FilterType> implements ViewableClassDescription<FilterType> {
	FlexibleClassDescription classDescription;

	public AViewableClassDescription(FlexibleClassDescription aClassDescription) {
		this.classDescription = aClassDescription;
	}

	public FlexibleClassDescription getClassDescription() {
		return classDescription;
	}

	public void setClassDescription(FlexibleClassDescription classDescription) {
		this.classDescription = classDescription;
	}	

}
