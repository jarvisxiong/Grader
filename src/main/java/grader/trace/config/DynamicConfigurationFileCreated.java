package grader.trace.config;

import grader.trace.execution.UserProcessExecutionStarted;

public class DynamicConfigurationFileCreated extends ConfigurationFileInfo{

	public DynamicConfigurationFileCreated(String aMessage,
			String aConfigurationFileName, Object aFinder) {
		super(aMessage, aConfigurationFileName, aFinder);
	}
	public static DynamicConfigurationFileCreated newCase(
			String aConfigurationFileName, Object aFinder) {
		String aMessage = "Dynamic Configuration File Created: " + aConfigurationFileName; 
				
		DynamicConfigurationFileCreated retVal = new DynamicConfigurationFileCreated(aMessage, aConfigurationFileName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
