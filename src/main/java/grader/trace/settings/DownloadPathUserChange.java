package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class DownloadPathUserChange extends GraderSettingsInfo {
	
	String downLoadPath;
	
	
	public DownloadPathUserChange(String aMessage, String aDownLoadPath, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		downLoadPath = aDownLoadPath;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public String getDownLoadPath() {
		return downLoadPath;
	}
	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + downLoadPath;
	}
	
	
	public static DownloadPathUserChange newCase(String aDownLoadPath, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "DownLoadPath Changed:" + aDownLoadPath;
		DownloadPathUserChange retVal = new DownloadPathUserChange(aMessage, aDownLoadPath,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
