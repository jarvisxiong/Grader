package grader.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigurationHolder {
	static PropertiesConfiguration staticConfiguration;
	
	static PropertiesConfiguration dynamicConfiguration;
	public static PropertiesConfiguration getStaticConfiguration() {
		return staticConfiguration;
	}
	public static void setStaticConfiguration(
			PropertiesConfiguration staticConfiguration) {
		ConfigurationHolder.staticConfiguration = staticConfiguration;
	}
	public static PropertiesConfiguration getDynamicConfiguration() {
		return dynamicConfiguration;
	}
	public static void setDynamicConfiguration(
			PropertiesConfiguration dynamicConfiguration) {
		ConfigurationHolder.dynamicConfiguration = dynamicConfiguration;
	}
	

}
