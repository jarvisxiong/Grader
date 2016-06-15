package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.SerializableGraderInfo;

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
	public static final int COLUMNS_USED = SerializableGraderInfo.COLUMNS_USED + 2; 

	@Override
	public String toCSVRow() {		
		return super.toCSVRow() 
				+ "," + overviewProjectStepper.getOnyen() + "," + 
				normalize(overviewProjectStepper.getName());}
	
	public static String onyenFromCSVRow(String[] aRow) {
		return aRow[COLUMNS_USED-2];
	}
	
	public static String nameFromCSVRow(String[] aRow) {
		return unNormalize(aRow[COLUMNS_USED-1]);
	}
	

}
