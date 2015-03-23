package grader.trace.config;

import grader.trace.GraderInfo;

public class ConfigurationFileInfo extends GraderInfo{
	String configurationFileName;

	

	public ConfigurationFileInfo(String aMessage, String aConfigurationFileName, Object aFinder) {
		super(aMessage, aFinder);
		configurationFileName = aConfigurationFileName;
	}
	public String getConfigurationFileName() {
		return configurationFileName;
	}

	public void setConfigurationFileName(String configurationFileName) {
		this.configurationFileName = configurationFileName;
	}
}
