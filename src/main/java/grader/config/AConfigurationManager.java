package grader.config;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import framework.utils.UserPropertyWriter;
import grader.trace.config.DynamicConfigurationFileCreated;
import grader.trace.config.DynamicConfigurationFileRead;
import grader.trace.config.StaticConfigurationFileNotRead;
import grader.trace.config.StaticConfigurationFileRead;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AConfigurationManager implements ConfigurationManager {

    public static final String CONFIG_DIR = "config";
    public static final String CONFIG_FILE = "config.properties";
    public static final String DYNAMIC_CONFIGURATION_FILE = "dynamicconfig.properties";
    public static final String MODULES_FILE = "modules.properties";
    public static final String DYNAMIC_MODULES_FILE = "dynamicmodules.properties";
    public static final String DYNAMIC_CONFIG_PROPERTY = "grader.dynamicConfiguration";
//    public static final String STATIC_CONFIGURATION_FILE_NAME = "./config/config.properties";
    public static final String STATIC_CONFIGURATION_FILE_NAME = "./" + CONFIG_DIR + "/" + CONFIG_FILE;
    public static final String DYNAMIC_CONFIGURATION_FILE_NAME = "./" + CONFIG_DIR + "/" + DYNAMIC_CONFIGURATION_FILE;

    public static final String MODULE_CONFIGURATION_FILE_NAME = "./" + CONFIG_DIR + "/" + MODULES_FILE;
    public static final String DYNAMIC_MODULE_CONFIGURATION_FILE_NAME = "./" + CONFIG_DIR + "/" + DYNAMIC_MODULES_FILE;


    private static PropertiesConfiguration staticConfiguration;
    static File userPropsFile;
    PropertiesConfiguration dynamicConfiguration;
    PropertiesConfiguration moduleConfiguration, dynamicModuleConfiguration;

    public AConfigurationManager() {
//		init();

    }

//    public void convertToDynamicConfiguration() {
//        Map<String, String> settings = GraderSettings.get().getSettings();
////	    	PropertiesConfiguration dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
//        for (String key : settings.keySet()) {
//            dynamicConfiguration.setProperty(key, settings.get(key));
//        }
//        try {
//            dynamicConfiguration.save();
//        } catch (ConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }

    public PropertiesConfiguration getDynamicConfiguration() {
        return dynamicConfiguration;
    }

    public void setDynamicConfiguration(
            PropertiesConfiguration dynamicConfiguration) {
        this.dynamicConfiguration = dynamicConfiguration;
    }

    public PropertiesConfiguration getStaticConfiguration() {
        return staticConfiguration;
    }

    public void setStaticConfiguration(PropertiesConfiguration staticConfiguration) {
        this.staticConfiguration = staticConfiguration;
    }
    @Override
    public PropertiesConfiguration getModuleConfiguration() {
        return moduleConfiguration;
    }
    @Override
    public void setModuleConfiguration(PropertiesConfiguration newVal) {
        this.moduleConfiguration = newVal;
    }
    
    @Override
    public PropertiesConfiguration getDynamicModuleConfiguration() {
        return dynamicModuleConfiguration;
    }
    @Override
    public void setDynamicModuleConfiguration(PropertiesConfiguration newVal) {
        this.dynamicModuleConfiguration = newVal;
    }


    public void init(String args[]) {
        try {
//			 PropertiesConfiguration configuration = new PropertiesConfiguration("./config/config.properties");
//			 PropertiesConfiguration configuration = new PropertiesConfiguration(STATIC_CONFIGURATION_FILE_NAME);
            PropertiesConfiguration configuration = createStaticConfiguration(args);

            StaticConfigurationFileRead.newCase(STATIC_CONFIGURATION_FILE_NAME, this);
            setStaticConfiguration(configuration);
//            String dynamicConfigurationName = configuration.getString("grader.dynamicConfiguration", "dynamicconfig.properties");
            String dynamicConfigurationName = configuration.getString(DYNAMIC_CONFIG_PROPERTY, DYNAMIC_CONFIGURATION_FILE_NAME);

            File file = new File(dynamicConfigurationName);
            if (!file.exists()) {
                file.createNewFile();
                DynamicConfigurationFileCreated.newCase(dynamicConfigurationName, this);
//	         	convertToDynamicConfiguration();
            }
//            dynamicConfiguration = new PropertiesConfiguration(dynamicConfigurationName);
            setDynamicConfiguration(new PropertiesConfiguration(dynamicConfigurationName));

            DynamicConfigurationFileRead.newCase(dynamicConfigurationName, this);
            
            setModuleConfiguration(createModuleConfiguration(args));
            setDynamicModuleConfiguration(new PropertiesConfiguration(DYNAMIC_MODULE_CONFIGURATION_FILE_NAME));        
            
            

//	         GraderSettings.get().convertToDynamicConfiguration();
        } catch (ConfigurationException e) {
            StaticConfigurationFileNotRead.newCase(STATIC_CONFIGURATION_FILE_NAME, this);
            System.err.println("Error loading config file.");
            System.err.println(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    PropertiesConfiguration createModuleConfiguration(String[] args) {
       
                try {
					return new PropertiesConfiguration(MODULE_CONFIGURATION_FILE_NAME);
				} catch (ConfigurationException e) {
					e.printStackTrace();
					return null;
				}
                
                // Andrew might need to add stuff like in the method below
        
    }

    PropertiesConfiguration createStaticConfiguration(String[] args) {
        userPropsFile = null;
        try {
            if (args.length == 0) {
                return new PropertiesConfiguration(STATIC_CONFIGURATION_FILE_NAME);
            }
//		           UserPropertyWriter userProperties = new UserPropertyWriter(Paths.get("config", "config.properties").toString());
            UserPropertyWriter userProperties = new UserPropertyWriter(Paths.get(CONFIG_DIR, CONFIG_FILE).toString());
// this seems to be Andrew's code duplicating the properties in a thread specific file
            
            userProperties.setUserProperties(args);
            String name = "properties-" + Thread.currentThread().getId();
            try {
                userPropsFile = Files.createTempFile(name, ".config").toFile();
            } catch (IOException e) {
                e.printStackTrace();
                userPropsFile = Paths.get("config", name + ".config").toFile();
            }
            if (userPropsFile.exists()) {
                userPropsFile.delete();
            }
            try {
                userPropsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            userProperties.writeUserProperties(userPropsFile);
            // Load the default config file
            PropertiesConfiguration configuration = new PropertiesConfiguration(userPropsFile);
            
            return configuration;
//			PropertiesConfiguration configuration = new PropertiesConfiguration(STATIC_CONFIGURATION_FILE_NAME);
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            //System.out.println("run done");
            if (userPropsFile != null) {
                userPropsFile.delete();
            }
        }
    }

}
