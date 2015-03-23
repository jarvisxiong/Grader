package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ModuleUserChange extends GraderSettingsInfo {
	
	String module;
	
	
	public ModuleUserChange(String aMessage, String aModule, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		module = aModule;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + module;
	}
	
	
	public static ModuleUserChange newCase(String aModule, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Module Changed:" + aModule;
		ModuleUserChange retVal = new ModuleUserChange(aMessage, aModule,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
