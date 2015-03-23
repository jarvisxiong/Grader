package grader.trace.config;

import grader.trace.execution.UserProcessExecutionStarted;

public class StaticConfigurationFileRead extends ConfigurationFileInfo{

	public StaticConfigurationFileRead(String aMessage,
			String aConfigurationFileName, Object aFinder) {
		super(aMessage, aConfigurationFileName, aFinder);
	}
	public static StaticConfigurationFileRead newCase(
			String aConfigurationFileName, Object aFinder) {
		String aMessage = "Configuration File read: " + aConfigurationFileName; 
				
		StaticConfigurationFileRead retVal = new StaticConfigurationFileRead(aMessage, aConfigurationFileName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
