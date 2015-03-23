package grader.trace.config;

import util.trace.UncheckedTraceableException;
import grader.trace.execution.UserProcessExecutionStarted;

public class StaticConfigurationFileNotRead extends UncheckedTraceableException{
	String configurationFileName;
	public StaticConfigurationFileNotRead(String aMessage,
			String aConfigurationFileName, Object aFinder) {
		super(aMessage, aFinder);
	}
	public String getConfigurationFileName() {
		return configurationFileName;
	}
	public void setConfigurationFileName(String configurationFileName) {
		this.configurationFileName = configurationFileName;
	}
	
	public static StaticConfigurationFileNotRead newCase(
			String aConfigurationFileName, Object aFinder) {
		String aMessage = "Configuration File Not Read: " + aConfigurationFileName; 
				
		StaticConfigurationFileNotRead retVal = new StaticConfigurationFileNotRead(aMessage, aConfigurationFileName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
