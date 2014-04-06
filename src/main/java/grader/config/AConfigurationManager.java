package grader.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.trace.config.DynamicConfigurationFileCreated;
import grader.trace.config.DynamicConfigurationFileRead;
import grader.trace.config.StaticConfigurationFileNotRead;
import grader.trace.config.StaticConfigurationFileRead;

public class AConfigurationManager implements ConfigurationManager {
	PropertiesConfiguration staticConfiguration;
	public static final String STATIC_CONFIGURATION_FILE_NAME = "./config/config.properties";
	
	public AConfigurationManager() {
		init();

	}
	
	public void init() {
		try {
//			 PropertiesConfiguration configuration = new PropertiesConfiguration("./config/config.properties");
			 PropertiesConfiguration configuration = new PropertiesConfiguration(STATIC_CONFIGURATION_FILE_NAME);
			 StaticConfigurationFileRead.newCase(STATIC_CONFIGURATION_FILE_NAME, this);
			 setStaticConfiguration(configuration);
	         String dynamicConfigurationName = configuration.getString("grader.dynamicConfiguration", "dynamicconfig.properties");

	         File file = new File(dynamicConfigurationName);
	         if (!file.exists()) {
	         	file.createNewFile();
	         	DynamicConfigurationFileCreated.newCase(dynamicConfigurationName, this);
//	         	convertToDynamicConfiguration();
	         }
		      dynamicConfiguration =  new PropertiesConfiguration(dynamicConfigurationName);
		      DynamicConfigurationFileRead.newCase(dynamicConfigurationName, this);


//	         GraderSettings.get().convertToDynamicConfiguration();
			}  catch (ConfigurationException e) {
				StaticConfigurationFileNotRead.newCase(STATIC_CONFIGURATION_FILE_NAME, this);
	            System.err.println("Error loading config file.");
	            System.err.println(e.getMessage());
	            e.printStackTrace();
	        
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	 public void convertToDynamicConfiguration() {
		 	Map<String, String> settings = GraderSettings.get().getSettings();
//	    	PropertiesConfiguration dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
	    	for (String key : settings.keySet())
	            dynamicConfiguration.setProperty(key, settings.get(key));
	    	try {
				dynamicConfiguration.save();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	
	PropertiesConfiguration dynamicConfiguration;
	public  PropertiesConfiguration getStaticConfiguration() {
		return staticConfiguration;
	}
	public  void setStaticConfiguration(
			PropertiesConfiguration staticConfiguration) {
		this.staticConfiguration = staticConfiguration;
	}
	public  PropertiesConfiguration getDynamicConfiguration() {
		return dynamicConfiguration;
	}
	public  void setDynamicConfiguration(
			PropertiesConfiguration dynamicConfiguration) {
		this.dynamicConfiguration = dynamicConfiguration;
	}
	

}
