package grader.trace.stepper;

import java.util.Date;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.SerializableGraderInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SerializableStepperInfo extends SerializableGraderInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	SakaiProject sakaiProject;
	public SerializableStepperInfo(String aMessage, 
			SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper,
			SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aFinder);
		sakaiProjectDatabase = aSakaiProjectDatabase;
		overviewProjectStepper = aProjectStepper;
		sakaiProject = aProject;
	}
		
	public SakaiProject getSakaiProject() {
		return sakaiProject;
	}


	public SakaiProjectDatabase getSakaiProjectDatabase() {
		return sakaiProjectDatabase;
	}

	public OverviewProjectStepper getOverviewProjectStepper() {
		return overviewProjectStepper;
	}
	public static final int COLUMNS_USED = SerializableGraderInfo.COLUMNS_USED + 1; 

	@Override
	public String toCSVRow() {
		return super.toCSVRow() 
				+ "," + overviewProjectStepper.getOnyen();
	}
	
	public static String onyenFromCSVRow(String[] aRow) {
		return aRow[COLUMNS_USED-1];
	}
	

}
