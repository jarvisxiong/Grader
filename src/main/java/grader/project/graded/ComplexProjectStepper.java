package grader.project.graded;

import bus.uigen.models.PropertyFocusListener;

public interface ComplexProjectStepper extends MainProjectStepper, PropertyFocusListener{

	MainProjectStepper getMainProjectStepper();

	void setMainProjectStepper(MainProjectStepper mainProjectStepper);

	

}
