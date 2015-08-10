package grader.trace.source;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.SerializableGraderInfo;
import grader.trace.file.FileInfo;
import grader.trace.project.SourceFolderIdentified;
import grader.trace.steppers.SerializableStepperInfo;

public class SourceTACommentsChanged extends SerializableStepperInfo {
	



	String text;
	
	public SourceTACommentsChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aText,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		text = aText;
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static final int COLUMNS_USED = SerializableStepperInfo.COLUMNS_USED + 1; 

	@Override
	public String toCSVRow() {
		return super.toCSVRow() 
				+ "," + normalize(text);
	}
	
	public static String taCommentsFromCSVRow(String[] aRow) {
		return unNormalize(aRow[COLUMNS_USED-1]);
	}

	

	public static SourceTACommentsChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aText,
			Object aFinder) {
		String aMessage =  "New TA comments for " +  aProjectStepper.getOnyen() + " with  text: " + aText;
		SourceTACommentsChanged retVal = new SourceTACommentsChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aText, aFinder);
		retVal.announce();
		return retVal;
	}

	
}
