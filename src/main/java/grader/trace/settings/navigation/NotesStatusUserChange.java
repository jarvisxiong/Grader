package grader.trace.settings.navigation;

import java.util.Date;

import grader.navigation.filter.NotesStatus;
import grader.settings.GraderSettingsModel;
import grader.trace.settings.GraderSettingsInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class NotesStatusUserChange extends GraderSettingsInfo {
	
	NotesStatus notesStatus;
	
	
	public NotesStatusUserChange(String aMessage, NotesStatus aNotesStatus, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		notesStatus = aNotesStatus;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public NotesStatus getNotesStatus() {
		return notesStatus;
	}
	public void setNotesStatus(NotesStatus notesStatus) {
		this.notesStatus = notesStatus;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + notesStatus;
	}
	
	
	public static NotesStatusUserChange newCase(NotesStatus aNotesStatus, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "NotesStatus Changed:" + aNotesStatus;
		NotesStatusUserChange retVal = new NotesStatusUserChange(aMessage, aNotesStatus,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
