package grader.trace.config;

import grader.trace.execution.UserProcessExecutionStarted;

public class DynamicConfigurationFileRead extends ConfigurationFileInfo{

	public DynamicConfigurationFileRead(String aMessage,
			String aConfigurationFileName, Object aFinder) {
		super(aMessage, aConfigurationFileName, aFinder);
	}
	public static DynamicConfigurationFileRead newCase(
			String aConfigurationFileName, Object aFinder) {
		String aMessage = "Dynamic Configuration File Read: " + aConfigurationFileName; 
				
		DynamicConfigurationFileRead retVal = new DynamicConfigurationFileRead(aMessage, aConfigurationFileName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
