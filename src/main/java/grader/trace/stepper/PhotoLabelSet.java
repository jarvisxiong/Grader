package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.models.LabelBeanModel;
import util.trace.TraceableInfo;

public class PhotoLabelSet extends StepperInfo {
	LabelBeanModel labelModel;




public LabelBeanModel getLabelModel() {
		return labelModel;
	}



	public void setLabelModel(LabelBeanModel labelModel) {
		this.labelModel = labelModel;
	}



public PhotoLabelSet(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, LabelBeanModel newVal,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		
		labelModel = newVal;
	}


	
	public static PhotoLabelSet newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, LabelBeanModel newVal,
			Object aFinder) {
		String aMessage = "Photo label text: " + newVal.getText() + " icon:" + newVal.getIcon();
		PhotoLabelSet retVal = new PhotoLabelSet(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
