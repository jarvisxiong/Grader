package grader.trace.config;


public class DynamicConfigurationFileWritten extends ConfigurationFileInfo{

	public DynamicConfigurationFileWritten(String aMessage,
			String aConfigurationFileName, Object aFinder) {
		super(aMessage, aConfigurationFileName, aFinder);
	}
	public static DynamicConfigurationFileWritten newCase(
			String aConfigurationFileName, Object aFinder) {
		String aMessage = "Dynamic Configuration File Written: " + aConfigurationFileName; 
				
		DynamicConfigurationFileWritten retVal = new DynamicConfigurationFileWritten(aMessage, aConfigurationFileName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
