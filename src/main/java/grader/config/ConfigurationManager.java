package grader.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public interface ConfigurationManager {
	public  PropertiesConfiguration getStaticConfiguration() ;
	public  void setStaticConfiguration(
			PropertiesConfiguration staticConfiguration) ;
	public  PropertiesConfiguration getDynamicConfiguration() ;
	public  void setDynamicConfiguration(
			PropertiesConfiguration dynamicConfiguration) ;
	void init();

}
