package grader.config;

public class ConfigurationManagerSelector {
	static ConfigurationManager configurationManager = new AConfigurationManager();

	public static ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	public static void setConfigurationManager(
			ConfigurationManager configurationManager) {
		ConfigurationManagerSelector.configurationManager = configurationManager;
	}
	

}
