package grader.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;

public class AConfigurationManager implements ConfigurationManager {
	PropertiesConfiguration staticConfiguration;
	
	public AConfigurationManager() {
		init();
//		try {
//		 PropertiesConfiguration configuration = new PropertiesConfiguration("./config/config.properties");
//         setStaticConfiguration(configuration);
//         String dynamicConfigurationHolder = configuration.getString("grader.dynamicConfiguration", "dynamicconfig.properties");
//         File file = new File(dynamicConfigurationHolder);
//         if (!file.exists()) {
//         	file.createNewFile();
//         	convertToDynamicConfiguration();
//         }
//        dynamicConfiguration =  new PropertiesConfiguration(dynamicConfigurationHolder);
//        setDynamicConfiguration(dynamicConfiguration);
//
////         GraderSettings.get().convertToDynamicConfiguration();
//		}  catch (ConfigurationException e) {
//            System.err.println("Error loading config file.");
//            System.err.println(e.getMessage());
//        
//        } catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void init() {
		try {
			 PropertiesConfiguration configuration = new PropertiesConfiguration("./config/config.properties");
	         setStaticConfiguration(configuration);
	         String dynamicConfigurationName = configuration.getString("grader.dynamicConfiguration", "dynamicconfig.properties");

	         File file = new File(dynamicConfigurationName);
	         if (!file.exists()) {
	         	file.createNewFile();
//	         	convertToDynamicConfiguration();
	         }
		      dynamicConfiguration =  new PropertiesConfiguration(dynamicConfigurationName);


//	         GraderSettings.get().convertToDynamicConfiguration();
			}  catch (ConfigurationException e) {
	            System.err.println("Error loading config file.");
	            System.err.println(e.getMessage());
	        
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
